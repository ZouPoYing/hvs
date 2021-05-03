package com.graduation.hvs.mapper;

import com.graduation.hvs.dao.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER")
    @Results({
            @Result(property = "userid", column = "userid"),
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email"),
            @Result(property = "usertype", column = "usertype"),
            @Result(property = "audit", column = "audit"),
            @Result(property = "date", column = "date")})
    List<User> queryAllUser();

    @Select("SELECT * FROM USER WHERE USERID=#{userid}")
    @Results({
            @Result(property = "userid", column = "userid"),
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email"),
            @Result(property = "usertype", column = "usertype"),
            @Result(property = "audit", column = "audit"),
            @Result(property = "date", column = "date")})
    User getUserById(Integer userid);

    @Select("SELECT USERID FROM USER WHERE USERNAME=#{username} AND PASSWORD=#{password}")
    @Results({
            @Result(property = "userid", column = "userid"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password") })
    Integer login(String username, String password);

    @Select("SELECT COUNT(*) FROM USER WHERE USERNAME=#{username}")
    Integer hasUsername(String username);

    @Select("INSERT INTO USER(USERNAME, PASSWORD, USERTYPE) VALUES (#{username}, #{password}, #{usertype})")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "usertype", column = "usertype")})
    void regist(String username, String password, Integer usertype);

    @Update("UPDATE USER SET USERNAME=#{username}, NAME=#{name}, PASSWORD=#{password}, " +
            "TELEPHONE=#{telephone}, EMAIL=#{email}, SEX=#{sex} WHERE USERID=#{userid}")
    @Results({
            @Result(property = "userid", column = "userid"),
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email")})
    void updateUser(Integer userid, String username, String name, String password, String telephone,
                    String email, String sex);

    @Update("UPDATE USER SET AUDIT=#{audit} WHERE USERID=#{userid}")
    @Results({
            @Result(property = "userid", column = "userid"),
            @Result(property = "audit", column = "audit")})
    void updateAuditByUserid(Integer userid, Integer audit);

    @Select("SELECT * FROM USER WHERE USERTYPE=2")
    @Results({
            @Result(property = "userid", column = "userid"),
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "department", column = "department"),
            @Result(property = "age", column = "age"),
            @Result(property = "level", column = "level"),
            @Result(property = "advantage", column = "advantage"),
            @Result(property = "date", column = "date")})
    List<Map<String, Object>> getDoctorDetail();

    @Insert("INSERT INTO USER(USERNAME, NAME, PASSWORD, TELEPHONE, EMAIL, SEX, USERTYPE, DEPARTMENT" +
            ", LEVEL, ADVANTAGE, AGE) VALUES (#{username}, #{name}, #{password}, #{telephone}, #{email}, " +
            "#{sex}, #{usertype}, #{department}, #{level}, #{advantage}, #{age})")
    void addDoctorDetail(String username, String name, String password, String telephone,
                         String email, String sex, Integer usertype, String department,
                         String age, String level, String advantage);

    @Update("UPDATE USER SET USERNAME=#{username}, NAME=#{name}, PASSWORD=#{password}, TELEPHONE=#{telephone}, " +
            "EMAIL=#{email}, SEX=#{sex}, USERTYPE=#{usertype}, DEPARTMENT=#{department}" +
            ", LEVEL=#{level}, ADVANTAGE=#{advantage}, AGE=#{age} WHERE USERID=#{userid}")
    void updateDoctorDetail(String username, String name, String password, String telephone,
                         String email, String sex, Integer usertype, String department,
                         String age, String level, String advantage, Integer userid);

    @Delete("DELETE FROM USER WHERE USERID=#{userid}")
    void deleteDoctorDetail(Integer userid);
}
