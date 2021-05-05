package com.graduation.hvs.mapper;

import com.graduation.hvs.dao.Set;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    @Insert("INSERT INTO SETS(FILENAME, FILEID) VALUES (#{filename}, #{fileid})")
    @Results({
            @Result(property = "filename", column = "filename"),
            @Result(property = "usertype", column = "usertype"),
            @Result(property = "px", column = "px"),
            @Result(property = "fileid", column = "fileid"),})
    void addSet(String filename, Integer fileid);

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

    @Select("SELECT * FROM SETS WHERE ID=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "usertype", column = "usertype"),
            @Result(property = "path", column = "path"),
            @Result(property = "date", column = "date"),
            @Result(property = "px", column = "px"),
            @Result(property = "fileid", column = "fileid")})
    Set selectById(Integer id);

    @Select("DELETE FROM SETS WHERE ID=#{id}")
    void deleteById(Integer id);

    @Update("UPDATE SETS SET FILENAME=#{filename}, PX=#{px} WHERE ID=#{id}")
    void updateSet(String filename, Integer px, Integer id);

    @Update("UPDATE SETS SET PX=0 WHERE ID<>#{id}")
    void updateOtherSet(Integer id);

    @Select("SELECT files.filename as filename from SETS LEFT JOIN files on sets.fileid=files.fileid where sets.px=1")
    @Results({
            @Result(property = "filename", column = "filename")})
    Map<String, Object> getSetFile();
}
