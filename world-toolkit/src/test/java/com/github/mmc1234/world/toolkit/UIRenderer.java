package com.github.mmc1234.world.toolkit;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.leg2.toolkit.renderer.UIMesh;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.LocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.Window;

import lombok.SneakyThrows;

public class UIRenderer {
  public int program, vao, pos, clip;
  private final ArrayList<UIMesh> meshs = new ArrayList<>();
  private float[] clipRect = new float[4];

  @SneakyThrows
  public void init() {
    int program = GL30.glCreateProgram();
    int vertexShader = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
    int fragmentShader = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
    GL30.glShaderSource(vertexShader, IOUtils
        .toString(TestUIRenderer.class.getClassLoader().getResource("clip/vertexShader.glsl"), Charset.defaultCharset()));
    GL30.glShaderSource(fragmentShader, IOUtils.toString(
        TestUIRenderer.class.getClassLoader().getResource("clip/fragmentShader.glsl"), Charset.defaultCharset()));
    GL30.glCompileShader(vertexShader);
    GL30.glCompileShader(fragmentShader);
    GL30.glAttachShader(program, vertexShader);
    GL30.glAttachShader(program, fragmentShader);
    GL30.glValidateProgram(program);
    GL30.glLinkProgram(program);
    GL30.glUseProgram(program);
    String log = GL30.glGetProgramInfoLog(program);
    System.out.print(log);

    vao = GL30.glGenVertexArrays();
    GL30.glBindVertexArray(vao);

    pos = GL30.glGenBuffers();
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, pos);
    GL30.glEnableVertexAttribArray(0);
    GL30.glVertexAttribPointer(0, 2, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);

    clip = GL30.glGenBuffers();
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, clip);
    GL30.glEnableVertexAttribArray(1);
    GL30.glVertexAttribPointer(1, 4, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
  }

  public void draw(UIMesh mesh) {
    meshs.add(mesh);
  }

  public int calculateSize() {
    int size = 0;
    for (UIMesh mesh : meshs) {
      size += mesh.pos.length / 2;
    }
    return size;
  }

  public void render() {
    GL30.glUseProgram(program);
    GL30.glEnable(GL30.GL_BLEND);
    GL30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
    GL30.glClearColor(0.4f, 0.4f, 0.4f, 1);
    GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);

    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, pos);
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, meshs.get(0).pos, GL30.GL_STREAM_DRAW);
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, new float[] {meshs.get(0).x, meshs.get(0).y, meshs.get(0).x1, meshs.get(0).y1}, GL30.GL_STREAM_DRAW);
    GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, 6);
  }
}
