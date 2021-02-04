package com.github.mmc1234.world.toolkit.gl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;

import com.github.mmc1234.world.toolkit.local.Window;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author mmc1234 @ This class maintains GL state to prevent frequent queries.
 * Whether he can improve performance needs to be tested by RenderDoc. Please do
 * not call the GL state function externally. It will result in incorrect
 * results.
 */
public class GLManager {
  protected final @Getter Logger logger;

  public GLManager() {
    logger = Logger.getLogger("GLManager");
  }

  /**
   * Activates the currently bound texture.
   */
  public void activeTexture(int unit) {
    if (unit < 32) {
      GL30.glActiveTexture(GL30.GL_TEXTURE0 + unit);
    }
  }

  public void bindArrayBuffer(int arrayBuffer) {
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, arrayBuffer);
  }

  public void bindFrameBuffer(int frameBuffer) {
    GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
  }

  public void bindTexture2D(int texture) {
    GL30.glBindTexture(GL30.GL_TEXTURE_2D, texture);
  }
  
  public void bindVertexArray(int vertexArray) {
    GL30.glBindVertexArray(vertexArray);
  }

  public void clearColor(double r, double g, double b, double a) {
    GL30.glClearColor((float)r, (float)g, (float)b, (float)a);
  }
  
  public int createBuffer() {
    return GL30.glGenBuffers();
  }
  
  public int createFrameBuffer() {
    return GL30.glGenFramebuffers();
  }
  
  public int createProgram() {
    return GL30.glCreateProgram();
  }
  
  public int createTexture() {
    return GL30.glGenTextures();
  }

  public int createVertexArray() {
    return GL30.glGenVertexArrays();
  }

  public void deleteBuffer(int arrayBuffer) {
    GL30.glDeleteBuffers(arrayBuffer);
  }

  public void deleteFrameBuffer(int fremaeBuffer) {
    GL30.glDeleteFramebuffers(fremaeBuffer);
  }

  public void deleteProgram(int program) {
    GL30.glDeleteProgram(program);
  }
  
  public void deleteTexture(int texture) {
    GL30.glDeleteTextures(texture);
  }

  public void deleteVertexArray(int vertexArray) {
    GL30.glDeleteVertexArrays(vertexArray);
  }

  public void disable(EnableTarget target) {
    GL30.glDisable(target.target);
  }

  public void enable(EnableTarget target) {
    GL30.glEnable(target.target);
  }
  
  /**
   * Returns object validity. Unused object are invalid.
   */
  public boolean isBuffer(int buffer) {
    return GL30.glIsBuffer(buffer);
  }

  public boolean isEnable(EnableTarget target) {
    return GL30.glGetBoolean(target.target);
  }

  public boolean isFrameBuffer(int frameBuffer) {
    return GL30.glIsFramebuffer(frameBuffer);
  }

  /**
   * Returns object validity.
   * */
  public boolean isProgram(int program) {
    return GL30.glIsProgram(program);
  }

  /**
   * Returns object validity. Unused object are invalid.
   */
  public boolean isRenderBuffer(int renderBuffer) {
    return GL30.glIsRenderbuffer(renderBuffer);
  }

  /**
   * Returns object validity.
   * */
  public boolean isShader(int shader) {
    return GL30.glIsShader(shader);
  }

  /**
  * Returns object validity. Unused object are invalid.
  */
  public boolean isTexture(int texture) {
    return GL30.glIsTexture(texture);
  }

  /**
  * Returns object validity. Unused object are invalid.
  */
  public boolean isVertexArray(int vertexArray) {
    return GL30.glIsVertexArray(vertexArray);
  }
  
  public void clearColor(float r, float g, float b, float a) {
    GL30.glClearColor(r, g, b, a);
  }

  float[] color = new float[4];
  
  public void useProgram(int program) {
    GL30.glUseProgram(program);
  }
}
