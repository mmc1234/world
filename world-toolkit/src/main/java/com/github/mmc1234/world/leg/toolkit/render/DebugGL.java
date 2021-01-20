package com.github.mmc1234.world.leg.toolkit.render;

import org.apache.log4j.Logger;
import static org.lwjgl.opengl.GL30.*;

public class DebugGL implements GL {
  public static final Logger LOG = Logger.getLogger(DebugGL.class);
  @Override
  public void activeTexture(int unit) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void attachShader(int program, int shader) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bindFramebuffer(EnumFramebuffer target, int framebuffer) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bindRenderbuffer(int renderbuffer) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bindVertexArray(int vao) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bindBuffer(EnumBuffer target, int buffer) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bindTexture(EnumTexture target, int texture) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void blendFunc(EnumBlendFunc sourceFactor, EnumBlendFunc destinationFactor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bufferData(EnumBuffer target, EnumDraw usage, Object data) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bufferDataSize(EnumBuffer target, EnumDraw usage, long size) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bufferSubData(EnumBuffer target, long offset, Object data) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void bind() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void disable(EnumState state) {
    if(state.isNormal) {
      glDisable(state.value);
    } else if(state == EnumState.ZWrite) {
      glDepthMask(false);
    }
  }

  @Override
  public void enable(EnumState state) {
    if(state.isNormal) {
      glEnable(state.value);
    } else if(state == EnumState.ZWrite) {
      glDepthMask(true);
    }
  }
  
  public void check(String message) {
    int error;
    while((error = glGetError())!=GL_NO_ERROR) {
      String d = "undefined error";
      switch (error) {
        case GL_INVALID_ENUM:
          d = "error enum.";
          break;
        case GL_INVALID_VALUE:
          d = "error value.";
          break;
        case GL_INVALID_OPERATION:
          d = "error operation.";
          break;
        case GL_STACK_OVERFLOW:
          d = "error stack overflow.";
          break;
        case GL_STACK_UNDERFLOW:
          d = "error stack underflow.";
          break;
        case GL_OUT_OF_MEMORY:
          d = "out of memory.";
          break;
      default:
        break;
      }
      LOG.warn(d+"\n"+message);
    }
  }
  

}
