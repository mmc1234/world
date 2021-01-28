package com.github.mmc1234.world.toolkit;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLDebugMessageCallback;
import org.lwjgl.opengl.GLDebugMessageCallbackI;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.window.LocalContext;
import com.github.mmc1234.world.toolkit.window.Window;

import lombok.SneakyThrows;

public abstract class BaseGraphTestApp implements Runnable {
  ILocalContext ctx;
  public final void run() {
    GLFW.glfwInit();
    ctx = new LocalContext();
    //GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4);
    Window window = new Window(null, null, "Render UI-0.1", 800, 600);
    window.start();
    
    ctx.make(window);
    ctx.loadGL();
    
    double lastTime = GLFW.glfwGetTime();
    final double FPS = 200;
    double t = 0;
    window.swapInterval(1);
    GLFW.glfwSetErrorCallback(new GLFWErrorCallbackI() {
      @Override
      public void invoke(int error, long description) {
        System.out.println("GLFW("+error+"):"+GLFWErrorCallback.getDescription(description)); 
      }
    });
    GL30.glEnable(KHRDebug.GL_DEBUG_OUTPUT);
    KHRDebug.glDebugMessageCallback(new GLDebugMessageCallbackI() {
      @Override
      public void invoke(int source, int type, int id, int severity, int length, long message, long userParam) {
        System.out.println(GLDebugMessageCallback.getMessage(length, message));
      }
    }, MemoryUtil.NULL);
    
    init();
    
    while(!window.isShouldClose()) {
      ctx.waitEvents(0.001);
      double time = GLFW.glfwGetTime();
      t = t+(time-lastTime);
      lastTime = time;
      if(t>(FPS/1000)) {
        t = t%(FPS/1000);
        render();
        window.swapBuffers();
      }
    }
  }
  
  public abstract void init();
  public abstract void render();
  
  @SneakyThrows
  int createProgram(String vs, String fs) {
    int program = GL30.glCreateProgram();
    int vertexShader = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
    int fragmentShader = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
    GL30.glShaderSource(vertexShader,
        IOUtils.toString(TestRender.class.getClassLoader().getResource(vs), Charset.defaultCharset()));
    GL30.glShaderSource(fragmentShader, IOUtils
        .toString(TestRender.class.getClassLoader().getResource(fs), Charset.defaultCharset()));
    GL30.glCompileShader(vertexShader);
    GL30.glCompileShader(fragmentShader);
    GL30.glAttachShader(program, vertexShader);
    GL30.glAttachShader(program, fragmentShader);
    GL30.glValidateProgram(program);
    GL30.glLinkProgram(program);
    System.out.println(GL30.glGetProgramInfoLog(program));
    return program;
  }
}
