package com.lagou.learn.mapl.sqlsession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import com.lagou.learn.mapl.executor.SimpleExecutor;
import com.lagou.learn.mapl.io.Configuration;
import com.lagou.learn.mapl.io.MappedStatement;

public class DefaultSqlSession implements SqlSession {

  private Configuration configuration;

  public DefaultSqlSession(Configuration configuration){
    this.configuration = configuration;
  }

  @Override public <T> List<T> selectList(String statmentId, Object... params) throws Exception {
    SimpleExecutor simpleExecutor =  new SimpleExecutor();
    MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statmentId);
    return simpleExecutor.query(configuration,mappedStatement,params);
  }

  @Override public <E> E selectOne(String statmentId, Object... params) throws Exception {

    SimpleExecutor simpleExecutor =  new SimpleExecutor();
    MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statmentId);
    List<E> list= simpleExecutor.query(configuration,mappedStatement,params);
    if(list.size() == 1){
      return list.get(0);
    }else {
      throw  new RuntimeException("查询错误");
    }
  }

  @Override public <E> E getMapper(Class<?> mapper) throws Exception {

    Object o = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[] {mapper},
        new InvocationHandler() {
          @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            String metohname = method.getName();
            String className = method.getDeclaringClass().getName();

            String statmentId = className+"."+metohname;

            if(metohname.contains("insert")){
              return insert(statmentId,args);

            }else if(metohname.contains("update")){
              return update(statmentId,args);

            }else if(metohname.contains("delete")){
              return delete(statmentId,args);

            }else{
              Type genericReturnType = method.getGenericReturnType();
              if(genericReturnType instanceof ParameterizedType){
                List<Object> objects = selectList(statmentId,args);
                return objects;
              }
              return selectOne(statmentId,args);
            }

          }
        });
    return (E) o;
  }

  @Override public int insert(String statmentId, Object... params) throws Exception {
    SimpleExecutor simpleExecutor =  new SimpleExecutor();
    MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statmentId);
    return simpleExecutor.insert(configuration,mappedStatement,params);
  }

  @Override public int update(String statmentId, Object... params) throws Exception {
    SimpleExecutor simpleExecutor =  new SimpleExecutor();
    MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statmentId);
    return simpleExecutor.update(configuration,mappedStatement,params);
  }

  @Override public int delete(String statmentId, Object... params) throws Exception {
    SimpleExecutor simpleExecutor =  new SimpleExecutor();
    MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statmentId);
    return simpleExecutor.delete(configuration,mappedStatement,params);
  }
}
