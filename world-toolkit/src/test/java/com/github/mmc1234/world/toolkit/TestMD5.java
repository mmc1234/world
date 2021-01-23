package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.security.MessageDigest;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

class TestMD5 {

  @Test
  void test() {
    String[] strHex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
    StringBuilder sb = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digest = md.digest(IOUtils.toByteArray(new FileInputStream(
          "C:\\Users\\Administrator\\.m2\\repository\\org\\projectlombok\\lombok-maven-plugin\\1.18.12.0\\lombok-maven-plugin-1.18.12.0.jar")));
      for (int i = 0; i < digest.length; i++) {
        int d = digest[i];
        if (d < 0)
          d += 256;
        sb.append(strHex[d / 16] + strHex[d % 16]);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(sb);
  }

}
