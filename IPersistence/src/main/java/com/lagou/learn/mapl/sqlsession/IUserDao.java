package com.lagou.learn.mapl.sqlsession;

import java.util.List;

import com.lagou.learn.mapl.model.User;

public interface IUserDao {

  public List<User> findAll() throws Exception;

  public User findAllByCondition(User user) throws Exception;

  public void insert(User user) throws Exception;

  public void update(User user) throws Exception;

  public void delete(User user) throws Exception;
}
