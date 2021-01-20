package com.github.mmc1234.world.toolkit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import com.github.mmc1234.world.toolkit.renderer.Window;

import lombok.SneakyThrows;

class TestWindow {  

  @Test
  @SneakyThrows
  void test() {
    GLFW.glfwInit();
    Window window = new Window();
    window.start();
    window.setOpacity(0.7f);
    window.setDecorated(true);
    for(int i = 0; i<30; i++) {
      new Thread(()-> { 
        while(true) {
          
        }
      }).start();
    }
    while(!window.isShouldClose()) {
      double time = GLFW.glfwGetTimerValue();
      GLFW.glfwWaitEventsTimeout(0.001);
      System.out.println((GLFW.glfwGetTimerValue()-time)/(GLFW.glfwGetTimerFrequency()/1000));
    }
    window.stop();
    
  }

}
