package com.lagou.learn.mapl.sqlsession;

import java.util.List;

public interface SqlSession {

  public <T> List<T> selectList(String statmentId,Object... params) throws Exception;

  public <E> E selectOne(String statmentId,Object... params) throws Exception;

  public <E> E getMapper(Class<?> mapper) throws Exception;

  public int insert(String statmentId, Object... params) throws Exception;

  public int update(String statmentId, Object... params) throws Exception;

  public int delete(String statmentId, Object... params) throws Exception;

}
