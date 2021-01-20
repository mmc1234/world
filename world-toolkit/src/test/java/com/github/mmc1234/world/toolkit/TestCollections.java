package com.github.mmc1234.world.toolkit;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.apache.commons.collections4.map.DefaultedMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.map.ListOrderedMap;
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
  
  @Test
  void apacheDefaultedMap() {
    Object NULL = new Object();
    org.apache.commons.collections4.map.DefaultedMap<String, Object> map = new DefaultedMap<String, Object>(NULL);
    for(int i = 0; i<WC; i++) {
      map.put("K"+i, i);
    }
    for(int i = 0; i<RC; i++) {
      map.get("K"+i);
    }
  }

}
