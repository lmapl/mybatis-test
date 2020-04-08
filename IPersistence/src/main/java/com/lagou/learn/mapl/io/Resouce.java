package com.lagou.learn.mapl.io;

import java.io.InputStream;

public class Resouce {

  public static InputStream getResourceAsStream(String path){
    return Resouce.class.getClassLoader().getResourceAsStream(path);
  }
}
