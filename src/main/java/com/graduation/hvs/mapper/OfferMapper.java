package com.graduation.hvs.mapper;

import com.graduation.hvs.dao.Offer;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface OfferMapper {

    @Select("SELECT * FROM OFFER")
    @Results({
            @Result(property = "offerid", column = "offerid"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "userid", column = "userid"),
            @Result(property = "money", column = "money"),
            @Result(property = "date", column = "date") })
    List<Offer> queryAllOffer();

    @Insert("INSERT INTO OFFER(ORDERID, USERID, MONEY) VALUES (#{orderid}, #{userid}, #{money})")
    void addOffer(Integer orderid, Integer userid, BigDecimal money);

    @Select("SELECT OFFER.OFFERID AS OFFERID,OFFER.ORDERID AS ORDERID,OFFER.USERID AS USERID,OFFER.MONEY " +
            "AS MONEY,OFFER.DATE AS DATE,USER.USERNAME AS USERNAME FROM OFFER LEFT JOIN USER ON " +
            "OFFER.USERID=USER.USERID WHERE ORDERID=#{orderid} ORDER BY MONEY")
    @Results({
            @Result(property = "offerid", column = "offerid"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "userid", column = "userid"),
            @Result(property = "money", column = "money"),
            @Result(property = "username", column = "username"),
            @Result(property = "date", column = "date") })
    List<Map<String, Object>> queryOfferByOrderid(Integer orderid);

    @Select("select v.* from (SELECT OFFER.OFFERID AS OFFERID,OFFER.ORDERID AS ORDERID,OFFER.USERID AS USERID,OFFER.MONEY " +
            "AS MONEY,OFFER.DATE AS DATE,USER.USERNAME AS USERNAME,row_number() over (partition by userid order " +
            "  by money) as group_idx FROM OFFER LEFT JOIN USER ON " +
            "OFFER.USERID=USER.USERID WHERE ORDERID=#{orderid} ORDER BY MONEY) v where v.group_idx= 1")
    @Results({
            @Result(property = "offerid", column = "offerid"),
            @Result(property = "orderid", column = "orderid"),
            @Result(property = "userid", column = "userid"),
            @Result(property = "money", column = "money"),
            @Result(property = "username", column = "username"),
            @Result(property = "date", column = "date") })
    List<Map<String, Object>> queryOneOfferByOrderid(Integer orderid);

}
