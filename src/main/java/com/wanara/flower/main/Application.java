package com.wanara.flower.main;

import com.wanara.flower.eu.BloomedEnum;
import com.wanara.flower.mapper.CharacterMapper;
import com.wanara.flower.model.Character;
import com.wanara.tools.Catcher;
import com.wanara.tools.ZlibTool;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {

        SqlSessionFactory sqlSessionFactory;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            sqlSession = sqlSessionFactory.openSession();
            CharacterMapper characterMapper = sqlSession.getMapper(CharacterMapper.class);
            List<Integer> charaIdList = characterMapper.selectCharaIdList();
            Map<Integer, String> charaIdMap = new HashMap<>();
            if(charaIdList != null && charaIdList.size() > 0){
                for (Integer charaId:
                     charaIdList) {
                    charaIdMap.put(charaId, "");
                }
            }

            syncData(charaIdMap);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    private static void syncData(Map<Integer, String> charaIdMap) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("Host", "dugrqaqinbtcq.cloudfront.net");
        requestMap.put("Origin", "http://dugrqaqinbtcq.cloudfront.net");
        requestMap.put("Referer", "http://dugrqaqinbtcq.cloudfront.net/product/ynnFQcGDLfaUcGhp/index.html");

        String title_voice_data = "http://dugrqaqinbtcq.cloudfront.net/product/ynnFQcGDLfaUcGhp/assets/voice/scenes/ec78a74ae5fee8bcae86693a00662d93.bin";
        byte[] result = Catcher.newInstance().setRequestMap(requestMap)
                .download(title_voice_data);
        if (result != null) {
            byte[] decompressResult = ZlibTool.decompress(result);
            try (Reader flowerReader = new StringReader(new String(decompressResult));
                 BufferedReader bufferedReader = new BufferedReader(flowerReader)) {
                String oneLine;
                List<Character> insertCharaList = new ArrayList<>();
                boolean ifFirstLine = true;
                while ((oneLine = bufferedReader.readLine()) != null) {
                    if (ifFirstLine){
                        ifFirstLine = false;
                        continue;
                    }
                    int firstIndex = oneLine.indexOf(",");
                    if (firstIndex > 0){
                        Integer charaId = Integer.parseInt(oneLine.substring(0, firstIndex));
                        String charaName = oneLine.substring(firstIndex + 1);
                        if (!charaIdMap.containsKey(charaId)){
                            insertCharaList.add(new Character(charaId, charaName, BloomedEnum.undefined.value));
                        }
                    }
                }
                System.out.println(insertCharaList.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
