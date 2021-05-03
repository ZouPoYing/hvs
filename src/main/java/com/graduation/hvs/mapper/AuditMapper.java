package com.graduation.hvs.mapper;

import com.graduation.hvs.dao.Audit;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuditMapper {

    @Select("SELECT * FROM AUDIT ORDER BY DATE DESC")
    @Results({
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "date", column = "date"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "auditerid", column = "auditerid"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "state", column = "state") })
    List<Audit> queryAllAudit();

    @Insert("INSERT INTO AUDIT(AUDITTYPE, COMMITTERID, FILEID, COMPANY, ADDRESS) VALUES (#{audittype}, " +
            "#{committerid}, #{fileid}, #{company}, #{address})")
    @Results({
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address")})
    void addAuditType1(Integer audittype, Integer committerid, Integer fileid, String company, String address);

    @Insert("INSERT INTO AUDIT(AUDITTYPE, COMMITTERID, FILEID, ORDERID) VALUES (2, " +
            "#{committerid}, #{fileid}, #{orderid})")
    @Results({
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address")})
    void addAuditType2(Integer committerid, Integer fileid, Integer orderid);

    @Insert("INSERT INTO AUDIT(AUDITTYPE, COMMITTERID, REASON, ORDERID) VALUES (3, " +
            "#{committerid}, #{reason}, #{orderid})")
    @Results({
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address")})
    void addAuditType3(Integer committerid, Integer orderid, String reason);

    @Select("SELECT * FROM AUDIT WHERE AUDITTYPE=#{audittype}")
    @Results({
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "date", column = "date"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "auditerid", column = "auditerid"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "state", column = "state") })
    List<Audit> queryAuditByAudittype(Integer audittype);

    @Update("UPDATE AUDIT SET STATE=#{state},AUDITERID=#{auditerid} WHERE AUDITID=#{auditid}")
    @Results({
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "auditerid", column = "auditerid"),
            @Result(property = "state", column = "state") })
    void AuditType1(Integer auditid,Integer auditerid,String state);

    @Select("SELECT AUDIT.COMPANY AS COMPANY,AUDIT.ADDRESS AS ADDRESS,AUDIT.AUDITID AS AUDITID,AUDIT.STATE AS STATE FROM AUDIT " +
            "LEFT JOIN USER ON AUDIT.COMMITTERID=USER.USERID WHERE AUDITTYPE=1 and USERID=#{userid}")
    @Results({
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "state", column = "state") })
    Map<String, Object> checkAuditType1(Integer userid);

    @Update("UPDATE AUDIT SET STATE='待审核', FILEID=#{fileid}, COMPANY=#{company}, ADDRESS=#{address} " +
            "WHERE AUDITID=#{auditid}")
    @Results({
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address"),
            @Result(property = "fileid", column = "fileid") })
    void modifyAuditType1(Integer fileid,Integer auditid,String company, String address);

    @Update("UPDATE AUDIT SET STATE='待审核', FILEID=#{fileid} WHERE AUDITID=#{auditid}")
    void modifyAuditType2(Integer fileid,Integer auditid);

    @Update("UPDATE AUDIT SET STATE='待审核', COMPANY=#{company}, ADDRESS=#{address} " +
            "WHERE AUDITID=#{auditid}")
    @Results({
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address") })
    void modifyAuditType1NoFileid(Integer auditid,String company, String address);

    @Update("UPDATE AUDIT SET STATE='待审核' WHERE AUDITID=#{auditid}")
    void modifyAuditType2NoFileid(Integer auditid);

    @Select("SELECT * FROM AUDIT WHERE AUDITID=#{auditid}")
    @Results({
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "date", column = "date"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "auditerid", column = "auditerid"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "state", column = "state") })
    Audit getAuditByAuditid(Integer auditid);

    @Update("UPDATE AUDIT SET STATE=#{state},AUDITERID=#{auditerid} WHERE AUDITID=#{auditid}")
    void AuditType2(Integer auditid,Integer auditerid,String state);

    @Select("SELECT V.* FROM (SELECT AUDIT.DATE AS DATE,AUDIT.REASON AS REASON,ORDERS.ORDERNAME AS ORDERNAME,ORDERS.MINMONEY\n" +
            "    AS MINMONEY,ORDERS.MAXMONEY AS MAXMONEY,OFFER.MONEY AS MONEY,ORDERS.ORDERID AS ORDERID,\n" +
            "    AUDIT.STATE AS STATE,AUDIT.AUDITID AS AUDITID,row_number() over (partition by ORDERID order by money) as group_idx\n" +
            "FROM AUDIT LEFT JOIN ORDERS ON AUDIT.ORDERID=ORDERS.ORDERID LEFT JOIN OFFER ON OFFER.ORDERID=\n" +
            "ORDERS.ORDERID WHERE AUDIT.AUDITTYPE=3 AND OFFER.USERID=ORDERS.ACCEPTERID) V WHERE V.GROUP_IDX=1")
    @Results({
            @Result(property = "date", column = "date"),
            @Result(property = "reason", column = "reason"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "maxmoney", column = "maxmoney"),
            @Result(property = "money", column = "money"),
            @Result(property = "state", column = "state"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "minmoney", column = "minmoney") })
    List<Map<String, Object>> getAuditType3();

    @Update("UPDATE AUDIT SET STATE='待审核',REASON=#{reason} WHERE AUDITID=#{auditid}")
    void updateAuditStateAndReason(String reason,Integer auditid);

}
