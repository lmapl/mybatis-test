package com.lagou.learn.mapl.io;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class XmlConfigBuilder {

  private Configuration configuration;

  public XmlConfigBuilder(){
    configuration = new Configuration();
  }

  public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
    Document document = new SAXReader().read(inputStream);
    Element root = document.getRootElement();
    List<Element> elementList =  root.selectNodes("//property");
    Properties properties = new Properties();
    for(Element element : elementList){
     String name = element.attributeValue("name");
     String value = element.attributeValue("value");
      properties.setProperty(name,value);

    }
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
    comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
    comboPooledDataSource.setUser(properties.getProperty("userName"));
    comboPooledDataSource.setPassword(properties.getProperty("passWord"));

    configuration.setDataSource(comboPooledDataSource);

    List<Element> mapperList =  root.selectNodes("//mapper");
    for(Element element : mapperList){
      String resource = element.attributeValue("resource");
      InputStream im =Resouce.getResourceAsStream(resource);
      XmlMapperBuilder mapperBuilder = new XmlMapperBuilder(configuration);
      mapperBuilder.parse(im);

    }

    return configuration;
  }
}
