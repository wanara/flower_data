package com.wanara.flower.main;

import com.wanara.flower.mapper.CharacterMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            sqlSession = sqlSessionFactory.openSession();
            CharacterMapper characterMapper = sqlSession.getMapper(CharacterMapper.class);
            System.out.println(characterMapper.selectChar(1));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null) sqlSession.close();
        }
    }
}
