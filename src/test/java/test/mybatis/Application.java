package test.mybatis;

import com.wanara.flower.mapper.CharacterMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Application {
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void init() {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void test() {
        sqlSession = sqlSessionFactory.openSession();
        //System.out.println(sqlSessionFactory);
        CharacterMapper mapper = sqlSession.getMapper(CharacterMapper.class);
        System.out.println(mapper.CreateCharacter());
//        System.out.println(mapper.selectAll());
        sqlSession.close();
    }
}
