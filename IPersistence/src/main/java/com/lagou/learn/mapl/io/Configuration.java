package com.lagou.learn.mapl.io;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class Configuration {

  private DataSource dataSource;

  private Map<String,MappedStatement> mappedStatementMap = new HashMap<String, MappedStatement>();

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Map<String, MappedStatement> getMappedStatementMap() {
    return mappedStatementMap;
  }

  public void setMappedStatementMap(
      Map<String, MappedStatement> mappedStatementMap) {
    this.mappedStatementMap = mappedStatementMap;
  }
}
