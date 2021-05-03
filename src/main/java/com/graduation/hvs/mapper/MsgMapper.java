package com.graduation.hvs.mapper;

import com.graduation.hvs.dao.Msg;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MsgMapper {

    @Select("SELECT * FROM MSG")
    @Results({
            @Result(property = "msgid", column = "msgid"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "date", column = "date"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "msgtype", column = "msgtype")})
    List<Msg> queryAllMsg();

    @Insert("INSERT INTO MSG(AUDITID, MSG, MSGTYPE) VALUES (#{auditid}, #{msg}, #{msgtype})")
    @Results({
            @Result(property = "msgid", column = "msgid"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "date", column = "date"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "msgtype", column = "msgtype")})
    void addMsg(Integer auditid, String msg, Integer msgtype);

    @Select("select msg.msgid as msgid,msg.msg as msg,(CASE msg.msgtype WHEN 0 THEN '退回' ELSE " +
            "'通过' END) as state,msg.date as date from msg left join audit on " +
            "msg.auditid=audit.auditid left join user on audit.committerid=user.userid where " +
            "audittype=1 and userid=#{userid} and msg.use=1 order by msg.date desc")
    @Results({
            @Result(property = "msgid", column = "msgid"),
            @Result(property = "state", column = "state"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "date", column = "date")})
    public List<Map<String, Object>> queryMsg1(Integer userid);

    @Select("select msg.msgid as msgid,msg.msg as msg,(CASE msg.msgtype WHEN 0 THEN '退回' ELSE " +
            "'通过' END) as state,msg.date as date from msg left join audit on " +
            "msg.auditid=audit.auditid left join user on audit.committerid=user.userid where " +
            "audittype=2 and userid=#{userid} and msg.use=1 order by msg.date desc")
    @Results({
            @Result(property = "msgid", column = "msgid"),
            @Result(property = "state", column = "state"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "date", column = "date")})
    public List<Map<String, Object>> queryMsg2(Integer userid);

    @Select("select msg.msgid as msgid,msg.msg as msg,msg.date as date from msg where msg.use=1 and msg.msgtype=11" +
            " and doctor=#{doctor}" +
            " order by date asc")
    @Results({
            @Result(property = "msgid", column = "msgid"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "date", column = "date")})
    List<Map<String, Object>> queryMsg11(Integer userid);

    @Select("select msg.msgid as msgid,msg.msg as msg,msg.date as date from msg where msg.use=1 and msg.msgtype=12" +
            " and patient=#{userid}" +
            " order by date desc")
    @Results({
            @Result(property = "msgid", column = "msgid"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "date", column = "date")})
    List<Map<String, Object>> queryMsg12(Integer userid);

    @Update("UPDATE MSG SET MSG.USE=0 WHERE MSGID=#{doctor}")
    void UpdateMsgUse(Integer msgid);

    @Insert("INSERT INTO MSG(AUDITID, MSG, isev) VALUES (#{auditid}, #{msg}, #{orderid})")
    @Results({
            @Result(property = "msgid", column = "msgid"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "date", column = "date"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "msgtype", column = "msgtype")})
    void addMsg2(Integer auditid, String msg, Integer orderid);

    @Insert("INSERT INTO MSG(MSG, MSGTYPE, PROCESSID, PATIENT, DOCTOR) VALUES (#{msg}, #{msgtype}, #{processid}, #{patient}, #{doctor})")
    void addMsgHvs(String msg, Integer msgtype, Integer processid, Integer patient, Integer doctor);

    @Select("select msg.msgid as msgid,msg.date as date,process.processid as processid\n" +
            ",process.processstep as processstep, process.patient as patient, \n" +
            "user.username as username,user.name as name, user.telephone as telephone,\n" +
            "user.email as email,user.age as age,user.sex as sex,process.filesid as filesid,\n" +
            "files.filename as filename\n" +
            "  from msg LEFT JOIN process ON msg.processid=process.processid\n" +
            " LEFT JOIN user ON process.patient=user.userid \n" +
            "LEFT JOIN files ON process.filesid=files.fileid\n" +
            "WHERE msgtype=11 and msg.doctor=#{doctor}")
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
            @Result(property = "filesid", column = "filesid"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "date", column = "date")})
    List<Map<String, Object>> getReception(Integer doctor);
}
