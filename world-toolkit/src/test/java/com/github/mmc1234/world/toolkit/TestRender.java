package com.github.mmc1234.world.toolkit;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.leg2.toolkit.renderer.ShaderProgram;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.LocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.Window;

import lombok.SneakyThrows;

class TestRender {

  @Test
  @SneakyThrows
  void test() {
    GLFW.glfwInit();
    ILocalContext ctx = new LocalContext(null);
    Window window = new Window(null, null, "Render Base", 800, 600);
    window.start();

    ctx.make(window);
    ctx.loadGL();

    double lastTime = GLFW.glfwGetTime();
    final double FPS = 75;
    double t = 0;
    window.swapInterval(1);

    int program = GL30.glCreateProgram();
    int vertexShader = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
    int fragmentShader = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
    GL30.glShaderSource(vertexShader,
        IOUtils.toString(TestRender.class.getClassLoader().getResource("vertexShader.glsl"), Charset.defaultCharset()));
    GL30.glShaderSource(fragmentShader, IOUtils
        .toString(TestRender.class.getClassLoader().getResource("fragmentShader.glsl"), Charset.defaultCharset()));
    GL30.glCompileShader(vertexShader);
    GL30.glCompileShader(fragmentShader);
    GL30.glAttachShader(program, vertexShader);
    GL30.glAttachShader(program, fragmentShader);
    GL30.glValidateProgram(program);
    GL30.glLinkProgram(program);
    String log = GL30.glGetProgramInfoLog(program);
    System.out.println(log);

    int vao = GL30.glGenVertexArrays();
    GL30.glBindVertexArray(vao);
    int pos = GL30.glGenBuffers();
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, pos);
    GL30.glEnableVertexAttribArray(0);
    GL30.glVertexAttribPointer(0, 2, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, new float[] { -1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1 },
        GL30.GL_STATIC_DRAW);

    while (!window.isShouldClose()) {
      ctx.waitEvents(0.001);
      double time = GLFW.glfwGetTime();
      t = t + (time - lastTime);
      lastTime = time;
      if (t > (FPS / 1000)) {
        int error = GL30.GL_NO_ERROR;
        GL30.glUseProgram(program);
        while ((error = GL30.glGetError()) != GL30.GL_NO_ERROR) {
          System.out.println(error);
        }
        t = t % (FPS / 1000);
        GL30.glClearColor(0.2f, 0.9f, 1.0f, 1);
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, 6);
        window.swapBuffers();
      }
    }
  }

  @Test
  @SneakyThrows
  void testClip() {
    GLFW.glfwInit();
    ILocalContext ctx = new LocalContext(null);
    Window window = new Window(null, null, "Render Clip", 800, 600);
    window.start();

    ctx.make(window);
    ctx.loadGL();

    double lastTime = GLFW.glfwGetTime();
    final double FPS = 75;
    double t = 0;
    window.swapInterval(1);

    int program = GL30.glCreateProgram();
    int vertexShader = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
    int fragmentShader = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
    GL30.glShaderSource(vertexShader, IOUtils
        .toString(TestRender.class.getClassLoader().getResource("clip/vertexShader.glsl"), Charset.defaultCharset()));
    GL30.glShaderSource(fragmentShader, IOUtils
        .toString(TestRender.class.getClassLoader().getResource("clip/fragmentShader.glsl"), Charset.defaultCharset()));
    GL30.glCompileShader(vertexShader);
    GL30.glCompileShader(fragmentShader);
    GL30.glAttachShader(program, vertexShader);
    GL30.glAttachShader(program, fragmentShader);
    GL30.glValidateProgram(program);
    GL30.glLinkProgram(program);
    String log = GL30.glGetProgramInfoLog(program);
    System.out.println(log);

    int vao = GL30.glGenVertexArrays();
    GL30.glBindVertexArray(vao);
    int pos = GL30.glGenBuffers();
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, pos);
    GL30.glEnableVertexAttribArray(0);
    GL30.glVertexAttribPointer(0, 2, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, new float[] { -1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1 },
        GL30.GL_STATIC_DRAW);

    int clip = GL30.glGenBuffers();
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, clip);
    GL30.glEnableVertexAttribArray(1);
    GL30.glVertexAttribPointer(1, 4, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, new float[] { 100, 100, 400, 400, 100, 100, 400, 400, 100, 100, 400, 400,
        100, 100, 400, 400, 100, 100, 400, 400, 100, 100, 400, 400, 100, 100, 400, 400, 100, 100, 400, 400 },
        GL30.GL_STATIC_DRAW);

    while (!window.isShouldClose()) {
      ctx.waitEvents(0.001);
      double time = GLFW.glfwGetTime();
      t = t + (time - lastTime);
      lastTime = time;
      if (t > (FPS / 1000)) {
        int error = GL30.GL_NO_ERROR;
        GL30.glUseProgram(program);
        while ((error = GL30.glGetError()) != GL30.GL_NO_ERROR) {
          System.out.println(error);
        }
        t = t % (FPS / 1000);
        GL30.glEnable(GL30.GL_BLEND);
        GL30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        GL30.glClearColor(0.4f, 0.4f, 0.4f, 1);
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, 6);
        window.swapBuffers();
      }
    }
  }

}
