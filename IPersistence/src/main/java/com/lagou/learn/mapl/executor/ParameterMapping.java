package com.lagou.learn.mapl.executor;

public class ParameterMapping {

  public ParameterMapping(String content){
    this.name = content;
  }
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
