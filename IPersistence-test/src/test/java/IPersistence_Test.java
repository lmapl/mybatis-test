import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.lagou.learn.mapl.io.Resouce;
import com.lagou.learn.mapl.model.User;
import com.lagou.learn.mapl.sqlsession.IUserDao;
import com.lagou.learn.mapl.sqlsession.SqlSession;
import com.lagou.learn.mapl.sqlsession.SqlSessionFactory;
import com.lagou.learn.mapl.sqlsession.SqlSessionFactoryBuilder;


public class IPersistence_Test {

  @Test
  public void test() throws Exception {
    /*InputStream resource = Resouce.getResourceAsStream("sqlMapperConfig.xml");
    if(resource == null){
      System.out.print("error");
    }

    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
   SqlSessionFactory sqlSessionFactory =  sqlSessionFactoryBuilder.build(resource);

     SqlSession sqlSession =  sqlSessionFactory.openSession();

    User user = new User();
    user.setId(1);
    user.setUsername("useranme1");
    User result =  sqlSession.selectOne("user.selectOne",user);
    System.out.print(result.getUsername());

    List<User> result1 =  sqlSession.selectList("user.selectList",user);
    System.out.print(result1.size());*/


    InputStream resource = Resouce.getResourceAsStream("sqlMapperConfig.xml");
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory =  sqlSessionFactoryBuilder.build(resource);


    SqlSession sqlSession =  sqlSessionFactory.openSession();
    IUserDao mapper = sqlSession.getMapper(IUserDao.class);
    List<User> all = mapper.findAll();
    System.out.print(all.size());

    all = all.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());

    int id = all.get(all.size()-1).getId()+1;
    User user3 = new User();
    user3.setId(id);
    user3.setUsername("useranme3");
    mapper.insert(user3);

    all = mapper.findAll();
    System.out.println(all.size());

    user3.setUsername("update_3");
    mapper.update(user3);

    User update = mapper.findAllByCondition(user3);
    System.out.println(update != null);

    mapper.delete(user3);
    all = mapper.findAll();
    System.out.println(all.size());








  }

}
