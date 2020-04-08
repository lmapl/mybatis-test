package com.lagou.learn.mapl.executor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lagou.learn.mapl.io.BoundSql;
import com.lagou.learn.mapl.io.Configuration;
import com.lagou.learn.mapl.io.MappedStatement;

public class SimpleExecutor implements Executor {

  @Override
  public <E> List<E> query(
      Configuration configuration, MappedStatement mappedStatement, Object... params)
      throws Exception {

    PreparedStatement preparedStatement =  getPreparedStatement(configuration,mappedStatement,params);

    ResultSet resultSet =  preparedStatement.executeQuery();
    String resultTypeCalss = mappedStatement.getResultType();
    Class<?> resultType= getClassByType(resultTypeCalss);

    List<Object> objects = new ArrayList<>();

    while (resultSet.next()){
      Object o = resultType.newInstance();

      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      for(int i=1;i<resultSetMetaData.getColumnCount()+1;i++){
       String columeName =  resultSetMetaData.getColumnName(i);
        Object value = resultSet.getObject(columeName);
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columeName, resultType);
        Method writeMethod = propertyDescriptor.getWriteMethod();
        writeMethod.invoke(o,value);
      }
      objects.add(o);

    }

    return (List<E>) objects;
  }

  @Override public int insert(Configuration configuration, MappedStatement mappedStatement, Object[] params)
      throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    PreparedStatement preparedStatement =  getPreparedStatement(configuration,mappedStatement,params);
    preparedStatement.execute();
    return 1;
  }

  @Override public int update(Configuration configuration, MappedStatement mappedStatement, Object[] params)
      throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    PreparedStatement preparedStatement =  getPreparedStatement(configuration,mappedStatement,params);
    preparedStatement.execute();
    return 1;
  }

  @Override public int delete(Configuration configuration, MappedStatement mappedStatement, Object[] params)
      throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
    PreparedStatement preparedStatement =  getPreparedStatement(configuration,mappedStatement,params);
    preparedStatement.execute();
    return 1;
  }

  private Class<?> getClassByType(String paramterType) throws ClassNotFoundException {
   return Class.forName(paramterType);
  }

  private PreparedStatement getPreparedStatement(Configuration configuration, MappedStatement mappedStatement, Object[] params)
      throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    Connection connection = configuration.getDataSource().getConnection();

    String sql = mappedStatement.getSql();
    BoundSql boundSql1 = getBoundSql(sql);

    PreparedStatement preparedStatement = connection.prepareStatement(boundSql1.getSqlText());

    String paramterType = mappedStatement.getParameterType();
    Class<?> parameterType = getClassByType(paramterType);
    List<ParameterMapping>  list = boundSql1.getParameterMappings();
    for(int i=0;i<list.size();i++){
      String content = list.get(i).getName();
      Field declaredField = parameterType.getDeclaredField(content);
      declaredField.setAccessible(true);
      Object object =  declaredField.get(params[0]);
      preparedStatement.setObject(i+1,object);

    }
    return preparedStatement;
  }

  private BoundSql  getBoundSql(String sql) {

    ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
    GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);
    String boundSql = genericTokenParser.parse(sql);

    List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

    BoundSql boundSql1 = new BoundSql(boundSql,parameterMappings);
    return boundSql1;
  }
}
