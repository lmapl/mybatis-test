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

public class XmlMapperBuilder {

  private Configuration configuration;

  public XmlMapperBuilder(Configuration configuration){
    this.configuration = configuration;
  }

  public void parse(InputStream inputStream) throws DocumentException {
    Document document = new SAXReader().read(inputStream);
    Element element =  document.getRootElement();

    String nameSpace = element.attributeValue("namespace");
//
    List<Element> elements =  element.selectNodes("//select");
    for(Element element1: elements){
      String id = element1.attributeValue("id");
      String resultType = element1.attributeValue("resultType");

      String parameterType = element1.attributeValue("parameterType");
      String sql = element1.getTextTrim();

      MappedStatement mappedStatement = new MappedStatement();
      mappedStatement.setId(id);
      mappedStatement.setResultType(resultType);
      mappedStatement.setParameterType(parameterType);
      mappedStatement.setSql(sql);

      String key = nameSpace + "."+id;
      configuration.getMappedStatementMap().put(key,mappedStatement);

    }

    List<Element> insertlements =  element.selectNodes("//insert");
    for(Element element1: insertlements){
      String id = element1.attributeValue("id");
      String resultType = element1.attributeValue("resultType");

      String parameterType = element1.attributeValue("parameterType");
      String sql = element1.getTextTrim();

      MappedStatement mappedStatement = new MappedStatement();
      mappedStatement.setId(id);
      mappedStatement.setResultType(resultType);
      mappedStatement.setParameterType(parameterType);
      mappedStatement.setSql(sql);

      String key = nameSpace + "."+id;
      configuration.getMappedStatementMap().put(key,mappedStatement);

    }

    List<Element> updatelements =  element.selectNodes("//update");
    for(Element element1: updatelements){
      String id = element1.attributeValue("id");
      String resultType = element1.attributeValue("resultType");

      String parameterType = element1.attributeValue("parameterType");
      String sql = element1.getTextTrim();

      MappedStatement mappedStatement = new MappedStatement();
      mappedStatement.setId(id);
      mappedStatement.setResultType(resultType);
      mappedStatement.setParameterType(parameterType);
      mappedStatement.setSql(sql);

      String key = nameSpace + "."+id;
      configuration.getMappedStatementMap().put(key,mappedStatement);

    }

    List<Element> deletelements =  element.selectNodes("//delete");
    for(Element element1: deletelements){
      String id = element1.attributeValue("id");
      String resultType = element1.attributeValue("resultType");

      String parameterType = element1.attributeValue("parameterType");
      String sql = element1.getTextTrim();

      MappedStatement mappedStatement = new MappedStatement();
      mappedStatement.setId(id);
      mappedStatement.setResultType(resultType);
      mappedStatement.setParameterType(parameterType);
      mappedStatement.setSql(sql);

      String key = nameSpace + "."+id;
      configuration.getMappedStatementMap().put(key,mappedStatement);

    }
  }
}
