package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.github.mmc1234.world.toolkit.gui.DemoBatch;
import com.github.mmc1234.world.toolkit.gui.IBatch;

class TestDemoBatch {

  @Test
  void test() {
    IBatch batch = new DemoBatch();
    batch.flush();
    batch.gc();
  }

}
