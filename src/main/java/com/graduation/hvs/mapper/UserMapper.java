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
            "TELEPHONE=#{telephone}, EMAIL=#{email}, SEX=#{sex}, AGE=#{age}, ADDRESS=#{address} WHERE USERID=#{userid}")
    void updateUser(Integer userid, String username, String name, String password, String telephone,
                    String email, String sex, String age, String address);

    @Update("UPDATE USER SET AUDIT=#{audit} WHERE USERID=#{userid}")
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

    @Select("SELECT * FROM USER WHERE USERTYPE=1")
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
    List<Map<String, Object>> getPDetail();

    @Insert("INSERT INTO USER(USERNAME, NAME, PASSWORD, TELEPHONE, EMAIL, SEX, USERTYPE, DEPARTMENT" +
            ", LEVEL, ADVANTAGE, AGE, ADDRESS) VALUES (#{username}, #{name}, #{password}, #{telephone}, #{email}, " +
            "#{sex}, #{usertype}, #{department}, #{level}, #{advantage}, #{age}, #{address})")
    void addDoctorDetail(String username, String name, String password, String telephone,
                         String email, String sex, Integer usertype, String department,
                         String age, String level, String advantage, String address);

    @Update("UPDATE USER SET USERNAME=#{username}, NAME=#{name}, PASSWORD=#{password}, TELEPHONE=#{telephone}, " +
            "EMAIL=#{email}, SEX=#{sex}, USERTYPE=#{usertype}, DEPARTMENT=#{department}" +
            ", LEVEL=#{level}, ADVANTAGE=#{advantage}, AGE=#{age}, ADDRESS=#{address} WHERE USERID=#{userid}")
    void updateDoctorDetail(String username, String name, String password, String telephone,
                         String email, String sex, Integer usertype, String department,
                         String age, String level, String advantage, String address, Integer userid);

    @Delete("DELETE FROM USER WHERE USERID=#{userid}")
    void deleteDoctorDetail(Integer userid);

    @Select("SELECT * FROM USER WHERE USERTYPE=0")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email")})
    List<Map<String, Object>> getAdmin();

    @Select("SELECT DISTINCT level FROM user where level is not null")
    @Results({
            @Result(property = "level", column = "level")})
    List<String> getLevel();

    @Select("SELECT DISTINCT department FROM user where department is not null")
    @Results({
            @Result(property = "department", column = "department")})
    List<String> getDepartment();

    @Select("SELECT DISTINCT userid,name FROM user where usertype=2 ${sub}")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "userid", column = "userid")})
    List<Map<String, Object>> getTJDoctor(String sub);

    @Select("SELECT lefttable.step as step,IFNULL(righttable.num,'0') as num\n" +
            "FROM  (SELECT x.d as step  FROM  (SELECT '挂号' AS d UNION ALL SELECT '就诊' " +
            "UNION ALL SELECT '缴费' UNION ALL SELECT '取药' UNION ALL SELECT '结束') x) as lefttable\n" +
            "LEFT JOIN  (SELECT af2.processstep as m,count(1) as num  from process af2 LEFT JOIN user " +
            "ON af2.doctor=user.userid where 1=1 ${sub} GROUP BY m) as righttable  ON lefttable.step=righttable.m")
    @Results({
            @Result(property = "step", column = "step"),
            @Result(property = "num", column = "num")})
    List<Map<String, Object>> getStatistics(String sub);

    @Select("SELECT af2.processstep as m,count(1) as num  from process af2 LEFT JOIN user ON " +
            "af2.doctor=user.userid where 1=1 ${sub} GROUP BY m\n")
    @Results({
            @Result(property = "name", column = "m"),
            @Result(property = "value", column = "num")})
    List<Map<String, Object>> getStatisticsPie(String sub);
}
