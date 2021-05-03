package com.graduation.hvs.mapper;

import com.graduation.hvs.dao.Order;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM ORDERS")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "accepterid", column = "accepterid"),
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "minmoney", column = "minmoney"),
            @Result(property = "maxmoney", column = "maxmoney"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "date", column = "date") })
    List<Order> queryAllOrder();

    @Insert("INSERT INTO ORDERS(ORDERNAME, TECHNOLOGY, MINMONEY, ENDDATE, COMMITTERID ,AUDITTYPE) VALUES " +
            "(#{ordername}, #{technology}, #{minmoney}, #{enddate}, #{committerid}, 2)")
    void addOrder(String ordername, String technology, BigDecimal minmoney, Date enddate, Integer committerid);

    @Select("SELECT * FROM ORDERS ORDER BY DATE DESC")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "accepterid", column = "accepterid"),
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "minmoney", column = "minmoney"),
            @Result(property = "maxmoney", column = "maxmoney"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "date", column = "date") })
    List<Order> queryAllLatest();

    @Select("SELECT ORDERS.ORDERID AS ORDERID,ORDERS.COMMITTERID AS COMMITTERID,AUDIT.STATE AS STATE " +
            ",ORDERS.DATE AS DATE,ORDERS.ORDERNAME AS ORDERNAME,ORDERS.TECHNOLOGY AS TECHNOLOGY," +
            "ORDERS.MINMONEY AS MINMONEY,AUDIT.FILEID AS FILEID,FILES.FILENAME AS FILENAME " +
            ",ORDERS.ENDDATE AS ENDDATE FROM ORDERS LEFT JOIN AUDIT ON AUDIT.ORDERID=ORDERS.ORDERID " +
            "LEFT JOIN FILES ON AUDIT.FILEID=FILES.FILEID " +
            "WHERE ORDERS.COMMITTERID=#{committerid} AND ORDERS.AUDITTYPE=2")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "state", column = "state"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "minmoney", column = "minmoney"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "date", column = "date") })
    List<Map<String, Object>> getOrderAudittype2(Integer committerid);

    @Select("SELECT ORDERS.ORDERID AS ORDERID,ORDERS.COMMITTERID AS COMMITTERID,AUDIT.STATE AS STATE " +
            ",ORDERS.DATE AS DATE,ORDERS.ORDERNAME AS ORDERNAME,ORDERS.TECHNOLOGY AS TECHNOLOGY," +
            "ORDERS.MINMONEY AS MINMONEY,AUDIT.FILEID AS FILEID,FILES.FILENAME AS FILENAME," +
            "USER.USERNAME AS USERNAME,AUDIT.AUDITID AS AUDITID,ORDERS.ENDDATE AS ENDDATE " +
            "FROM ORDERS LEFT JOIN AUDIT ON AUDIT.ORDERID=ORDERS.ORDERID " +
            "LEFT JOIN FILES ON AUDIT.FILEID=FILES.FILEID LEFT JOIN USER ON ORDERS.COMMITTERID=USER.USERID " +
            "WHERE ORDERS.AUDITTYPE=2")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "state", column = "state"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "minmoney", column = "minmoney"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "username", column = "username"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "date", column = "date") })
    List<Map<String, Object>> queryAudit2ByAudittype();

    @Update("UPDATE ORDERS SET AUDITID=#{auditid},AUDITERID=#{auditerid} WHERE ORDERID=#{orderid}")
    void UpdateOrderAudit(Integer auditid,Integer auditerid,Integer orderid);

    @Select("SELECT * FROM ORDERS WHERE ORDERID=#{orderid}")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "accepterid", column = "accepterid"),
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "minmoney", column = "minmoney"),
            @Result(property = "maxmoney", column = "maxmoney"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "date", column = "date") })
    Order getOrderByOrderid(Integer orderid);

    @Select("SELECT ORDERS.*,FILES.FILENAME AS FILENAME FROM ORDERS LEFT JOIN AUDIT ON ORDERS.ORDERID=AUDIT.ORDERID" +
            " LEFT JOIN FILES ON AUDIT.FILEID=FILES.FILEID WHERE AUDIT.AUDITTYPE=2 AND ORDERS.ORDERID=#{orderid}")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "accepterid", column = "accepterid"),
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "minmoney", column = "minmoney"),
            @Result(property = "maxmoney", column = "maxmoney"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "date", column = "date") })
    Map<String, Object> getOrderAndFileByOrderid(Integer orderid);

    @Update("UPDATE ORDERS SET ORDERNAME=#{ordername},TECHNOLOGY=#{technology},MINMONEY=#{minmoney}" +
            ",ENDDATE=#{enddate} WHERE ORDERID=#{orderid}")
    void UpdateOrder(String ordername,String technology,BigDecimal minmoney,Date enddate,Integer orderid);

    @Select("SELECT * FROM ORDERS LEFT JOIN AUDIT ON AUDIT.ORDERID=ORDERS.ORDERID WHERE " +
            "AUDIT.AUDITTYPE=2 AND STATE='通过'")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "accepterid", column = "accepterid"),
            @Result(property = "audittype", column = "audittype"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "minmoney", column = "minmoney"),
            @Result(property = "maxmoney", column = "maxmoney"),
            @Result(property = "auditid", column = "auditid"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "date", column = "date") })
    List<Order> getBiddingList();

    @Select("SELECT USER.USERNAME AS USERNAME,USER.TELEPHONE AS TELEPHONE,USER.EMAIL AS EMAIL" +
            ",AUDIT.COMPANY AS COMPANY,AUDIT.ADDRESS AS ADDRESS,FILES.FILENAME AS FILENAME" +
            ",AUDIT.DATE AS DATE FROM ORDERS LEFT JOIN AUDIT ON ORDERS.COMMITTERID=AUDIT.COMMITTERID " +
            "LEFT JOIN USER ON USER.USERID=ORDERS.COMMITTERID LEFT JOIN FILES ON FILES.FILEID=AUDIT.FILEID " +
            "WHERE AUDIT.AUDITTYPE=1 AND ORDERS.ORDERID=#{orderid}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "date", column = "date") })
    List<Map<String, Object>> getApplicant(Integer orderid);

    @Update("UPDATE ORDERS SET MAXMONEY=#{money} WHERE ORDERID=#{orderid}")
    void UpdateOrderMaxmoney(BigDecimal money,Integer orderid);

    @Select("SELECT COUNT(1) FROM ORDERS WHERE ORDERID=#{orderid} AND COMMITTERID=#{userid}")
    int isMyOrder(Integer orderid, Integer userid);

    @Select("SELECT USER.USERNAME AS USERNAME,USER.TELEPHONE AS TELEPHONE,USER.EMAIL AS EMAIL\n" +
            "     ,AUDIT.COMPANY AS COMPANY,AUDIT.ADDRESS AS ADDRESS,FILES.FILENAME AS FILENAME\n" +
            "     ,AUDIT.DATE AS DATE FROM USER LEFT JOIN AUDIT ON AUDIT.COMMITTERID=USER.USERID\n" +
            "    LEFT JOIN FILES ON AUDIT.FILEID=FILES.FILEID WHERE AUDIT.AUDITTYPE=1 AND USER.USERID IN\n" +
            "            (SELECT DISTINCT OFFER.USERID FROM OFFER LEFT JOIN ORDERS ON ORDERS.ORDERID=OFFER.ORDERID\n" +
            "                    WHERE ORDERS.ORDERID=#{orderid})")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email"),
            @Result(property = "company", column = "company"),
            @Result(property = "address", column = "address"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "date", column = "date") })
    List<Map<String, Object>> getBidder(Integer orderid);

    @Select("SELECT ORDERS.ORDERID AS ORDERID,ORDERS.COMMITTERID AS COMMITTERID,AUDIT.STATE AS STATE " +
            ",ORDERS.DATE AS DATE,ORDERS.ORDERNAME AS ORDERNAME,ORDERS.TECHNOLOGY AS TECHNOLOGY," +
            "ORDERS.MINMONEY AS MINMONEY,ORDERS.MAXMONEY AS MAXMONEY,AUDIT.FILEID AS FILEID,FILES.FILENAME AS FILENAME " +
            ",ORDERS.ENDDATE AS ENDDATE FROM ORDERS LEFT JOIN AUDIT ON AUDIT.ORDERID=ORDERS.ORDERID " +
            "LEFT JOIN FILES ON AUDIT.FILEID=FILES.FILEID " +
            "WHERE ORDERS.COMMITTERID=#{userid} AND ORDERS.ENDDATE<NOW() AND (ORDERS.AUDITTYPE=2 " +
            "OR (ORDERS.AUDITTYPE=3 AND AUDIT.STATE='退回'))")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "committerid", column = "committerid"),
            @Result(property = "state", column = "state"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "minmoney", column = "minmoney"),
            @Result(property = "maxmoney", column = "maxmoney"),
            @Result(property = "fileid", column = "fileid"),
            @Result(property = "filename", column = "filename"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "date", column = "date") })
    List<Map<String, Object>> getMyTimeOutOrder(Integer userid);

    @Update("UPDATE ORDERS SET AUDITTYPE=3,AUDITID=#{auditid},ACCEPTERID=#{accepterid} WHERE ORDERID=#{orderid}")
    void UpdateOrderAudittypeAndAuditid(Integer auditid,Integer accepterid,Integer orderid);

    @Update("UPDATE ORDERS SET AUDITTYPE=4,ACCEPTERID=#{accepterid} WHERE ORDERID=#{orderid}")
    void UpdateOrderAudit24(Integer accepterid,Integer orderid);

    @Update("UPDATE ORDERS SET AUDITTYPE=4 WHERE ORDERID=#{orderid}")
    void UpdateOrderAudit242(Integer orderid);

    @Select("select user.userid,orders.orderid,user.username,orders.ordername,orders.date" +
            ",orders.technology,orders.enddate from orders \n" +
            "    left join user on orders.accepterid = user.userid where orders.audittype=4 " +
            "and orders.committerid=#{committerid}")
    @Results({
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "userid", column = "userid"),
            @Result(property = "ordername", column = "ordername"),
            @Result(property = "technology", column = "technology"),
            @Result(property = "date", column = "date"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "username", column = "username") })
    List<Map<String, Object>> getEvaluate(Integer committerid);

    @Update("UPDATE ORDERS SET AUDITTYPE=5 WHERE ORDERID=#{orderid}")
    void UpdateOrderAudit25(Integer orderid);
}
