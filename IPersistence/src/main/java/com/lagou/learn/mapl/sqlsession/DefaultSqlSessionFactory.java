package com.lagou.learn.mapl.sqlsession;

import com.lagou.learn.mapl.io.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
  private Configuration configuration;

  public DefaultSqlSessionFactory(Configuration configuration){
    this.configuration =configuration;
  }

  @Override public SqlSession openSession() {
    return new DefaultSqlSession(configuration);
  }
}
