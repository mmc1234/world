package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

class TestTimer {

  @Test
  @SneakyThrows
  void test() {
    for(int i = 0; i<3; i++) {
      new Thread(()-> { 
        while(true) {
          
        }
      }).start();
    }
    while(true) {
      long c = 0;
      for(int i = 0; i<100; i++) {
        long time = System.nanoTime();
        TimeUnit.NANOSECONDS.sleep(1);
        c=c+(System.nanoTime()-time);
      }
      System.out.println(c/1000_000_000d/0.1);
    }
  }

}
