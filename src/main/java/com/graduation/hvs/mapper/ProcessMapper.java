package com.graduation.hvs.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProcessMapper {

    @Insert("INSERT INTO PROCESS(patient,processid, doctor, filesid, processstep) VALUES (#{patient}, #{doctor}, #{filesid}, '挂号')")
    void addDoctorDetail(Integer patient, Integer doctor, Integer filesid);

    @Select("SELECT COUNT(*) FROM PROCESS WHERE PATIENT=#{patient} AND PROCESSSTEP!='结束'")
    Integer hasProcessing(Integer patient);

    @Select("SELECT * FROM PROCESS ORDER BY DATE desc")
    @Results({
            @Result(property = "processid", column = "processid")})
    Integer selectProcessid();

    @Select("SELECT COUNT(*) FROM PROCESS WHERE DOCTOR=#{doctor} AND PROCESSSTEP='就诊'")
    Integer hasReceptioning(Integer doctor);

    @Update("UPDATE PROCESS SET processstep='就诊' WHERE processid=#{processid}")
    void reception(Integer processid);

    @Select("select msg.msgid as msgid,msg.date as date,process.processid as processid\n" +
            ",process.processstep as processstep, process.patient as patient, \n" +
            "user.username as username,user.name as name, user.telephone as telephone,\n" +
            "user.email as email,user.age as age,user.sex as sex,process.filesid as filesid,\n" +
            "files.filename as filename,user.address as address\n" +
            "  from msg LEFT JOIN process ON msg.processid=process.processid\n" +
            " LEFT JOIN user ON process.patient=user.userid \n" +
            "LEFT JOIN files ON process.filesid=files.fileid\n" +
            "WHERE msgtype=11 and process.processstep='就诊' and msg.doctor=#{doctor} order by date")
    @Results({
            @Result(property = "msgid", column = "msgid"),
            @Result(property = "processid", column = "processid"),
            @Result(property = "processstep", column = "processstep"),
            @Result(property = "patient", column = "patient"),
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email"),
            @Result(property = "age", column = "age"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(property = "filesid", column = "filesid"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "date", column = "date")})
    List<Map<String, Object>> getReception(Integer doctor);

    @Update("UPDATE PROCESS SET filesid=#{fileid} WHERE processid=#{processid}")
    void updateFileid(Integer fileid,Integer processid);

    @Update("UPDATE PROCESS SET processstep=#{processstep},vformid=#{vformid} WHERE processid=#{processid}")
    void updateProcessstepByProcessid(String processstep, Integer vformid, Integer processid);

    @Select("SELECT * FROM PROCESS WHERE processid=#{processid}")
    @Results({
            @Result(property = "processstep", column = "processstep")})
    Map<String, Object> selectProcessByProcessid(Integer processid);

    @Update("UPDATE PROCESS SET processstep=#{processstep} WHERE processid=#{processid}")
    void updatePByProcessid(String processstep, Integer processid);
}
