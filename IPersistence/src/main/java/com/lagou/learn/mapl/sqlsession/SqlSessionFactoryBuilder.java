package com.lagou.learn.mapl.sqlsession;

import java.beans.PropertyVetoException;
import java.io.InputStream;

import org.dom4j.DocumentException;

import com.lagou.learn.mapl.io.Configuration;
import com.lagou.learn.mapl.io.XmlConfigBuilder;

public class SqlSessionFactoryBuilder {

  public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
    XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
    Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);

    DefaultSqlSessionFactory defaultSqlSessionFactory =new DefaultSqlSessionFactory(configuration);
    return defaultSqlSessionFactory;
  }
}
