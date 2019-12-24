package com.wanara.flower.mapper;

import com.wanara.flower.model.Character;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CharacterMapper{
    @Select("select * from `character` where chara_id = #{id}")
    Character selectChar(@Param("id") Integer charId);

    @Update("create table flower.character_test(" +
            "chara_id int primary key not null, " +
            "char_name varchar(255) not null, " +
            "is_bloomed ENUM('Y','N','U') not null default 'U')")
    int CreateCharacter();
//    public List<Character> selectAll();
}
