package com.graduation.hvs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Offer {
    private Integer offerid;
    private Integer orderid;
    private Integer userid;
    private BigDecimal money;
    private Integer auditid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    @Override
    public String toString() {
        return "Offer{" +
                "offerid=" + offerid +
                ", orderid=" + orderid +
                ", userid=" + userid +
                ", money=" + money +
                ", auditid=" + auditid +
                ", date=" + date +
                '}';
    }

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
