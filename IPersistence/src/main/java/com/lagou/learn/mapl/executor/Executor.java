package com.lagou.learn.mapl.executor;

import java.sql.SQLException;
import java.util.List;

import com.lagou.learn.mapl.io.Configuration;
import com.lagou.learn.mapl.io.MappedStatement;

public interface Executor {

  public <E> List<E> query(Configuration configuration,MappedStatement mappedStatement,Object... params)
      throws Exception;

  int insert(Configuration configuration, MappedStatement mappedStatement, Object[] params)
      throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException;

  int update(Configuration configuration, MappedStatement mappedStatement, Object[] params)
      throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException;

  int delete(Configuration configuration, MappedStatement mappedStatement, Object[] params)
      throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException;
}
