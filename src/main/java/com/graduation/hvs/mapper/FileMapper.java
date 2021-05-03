package com.graduation.hvs.mapper;

import com.graduation.hvs.dao.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES(FILENAME, USERID) VALUES (#{filename}, #{userid})")
    @Results({
            @Result(property = "filename", column = "filename"),
            @Result(property = "userid", column = "userid")})
    void fileUpload(String filename, Integer userid);

    @Select("SELECT * FROM FILES WHERE FILENAME=#{filename}")
    @Results({
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "date", column = "date"),
            @Result(property = "userid", column = "userid")})
    File getFileByFilename(String filename);

    @Select("SELECT * FROM FILES WHERE FILEID=#{fileid}")
    @Results({
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "date", column = "date"),
            @Result(property = "userid", column = "userid")})
    File getFileByFileid(Integer fileid);
}
