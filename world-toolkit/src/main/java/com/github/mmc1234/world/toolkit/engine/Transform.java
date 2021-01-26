package com.github.mmc1234.world.toolkit.engine;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public interface Transform {
  public Vector3D position();
  public Vector3D rotate();
  public Vector3D scale();
}