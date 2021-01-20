package com.github.mmc1234.world.toolkit;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class TestCollections {

  public static final int RC = 6000*10, WC = 100000;
  
  @Test
  void javaHashMap() {
    HashMap<String, Object> map = new HashMap<>();
    for(int i = 0; i<WC; i++) {
      map.put("K"+i, i);
    }
    for(int i = 0; i<RC; i++) {
      map.get("K"+i);
    }
  }

}
