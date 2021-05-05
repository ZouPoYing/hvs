package com.graduation.hvs.mapper;

import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface MediMapper {

    @Insert("INSERT INTO MEDI(mediname, meditype, medimsg, num, money) VALUES (#{mediname}, #{meditype}, " +
            "#{medimsg}, #{num}, #{money})")
    void addMedi(String mediname, String meditype, String medimsg, Integer num, BigDecimal money);

    @Select("SELECT * FROM MEDI")
    @Results({
            @Result(property = "mediid", column = "mediid"),
            @Result(property = "mediname", column = "mediname"),
            @Result(property = "meditype", column = "meditype"),
            @Result(property = "medimsg", column = "medimsg"),
            @Result(property = "medinum", column = "medinum"),
            @Result(property = "medimoney", column = "medimoney"),
            @Result(property = "date", column = "date")})
    List<Map<String, Object>> getMediList();

    @Update("UPDATE MEDI SET mediname=#{mediname}, meditype=#{meditype}, medimsg=#{medimsg}, num=#{num}, " +
            "money=#{money} WHERE mediid=#{mediid}")
    void updateMedi(String mediname, String meditype, String medimsg, Integer num, BigDecimal money, Integer mediid);

    @Delete("DELETE FROM MEDI WHERE mediid=#{mediid}")
    void deleteMedi(Integer mediid);
}
