package com.github.mmc1234.world.leg2.toolkit.engine;

import java.util.Map;

public interface Screen extends Iterable<Node> {
  public boolean moveUp(Node obj);
  public boolean moveDown(Node obj);
  public boolean moveUp(Node obj, Node target);
  public boolean moveDown(Node obj, Node target);
  public boolean insert(Node obj, Node target);
  public boolean pushBack(Node obj);
  public boolean remove(Node obj);
  public Map<String, Object> getUserdata();
}