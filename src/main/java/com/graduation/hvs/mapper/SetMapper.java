package com.graduation.hvs.mapper;

import com.graduation.hvs.dao.Set;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SetMapper {
    @Select("SELECT * FROM SETS")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "usertype", column = "usertype"),
            @Result(property = "path", column = "path"),
            @Result(property = "date", column = "date"),
            @Result(property = "px", column = "px"),
            @Result(property = "fileid", column = "fileid")})
    List<Set> queryAllSet();

    @Insert("INSERT INTO SETS(FILENAME, USERTYPE, PX, FILEID) VALUES (#{filename}, #{usertype}, #{px}, #{fileid})")
    @Results({
            @Result(property = "filename", column = "filename"),
            @Result(property = "usertype", column = "usertype"),
            @Result(property = "px", column = "px"),
            @Result(property = "fileid", column = "fileid"),})
    void addSet(String filename, Integer usertype, Integer px, Integer fileid);

    @Select("SELECT * FROM SETS WHERE USERTYPE=#{usertype}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "usertype", column = "usertype"),
            @Result(property = "path", column = "path"),
            @Result(property = "date", column = "date"),
            @Result(property = "px", column = "px"),
            @Result(property = "fileid", column = "fileid")})
    Set getSetByUsertype(Integer usertype);
}
