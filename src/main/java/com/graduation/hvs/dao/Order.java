package com.graduation.hvs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer orderid;
    private Integer committerid;
    private Integer accepterid;
    private Integer audittype;
    private String ordername;
    private String technology;
    private BigDecimal minmoney;
    private BigDecimal maxmoney;
    private Integer auditid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date enddate;

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", committerid=" + committerid +
                ", accepterid=" + accepterid +
                ", audittype=" + audittype +
                ", ordername='" + ordername + '\'' +
                ", technology='" + technology + '\'' +
                ", minmoney=" + minmoney +
                ", maxmoney=" + maxmoney +
                ", auditid=" + auditid +
                ", date=" + date +
                ", enddate=" + enddate +
                '}';
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCommitterid() {
        return committerid;
    }

    public void setCommitterid(Integer committerid) {
        this.committerid = committerid;
    }

    public Integer getAccepterid() {
        return accepterid;
    }

    public void setAccepterid(Integer accepterid) {
        this.accepterid = accepterid;
    }

    public Integer getAudittype() {
        return audittype;
    }

    public void setAudittype(Integer audittype) {
        this.audittype = audittype;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public BigDecimal getMinmoney() {
        return minmoney;
    }

    public void setMinmoney(BigDecimal minmoney) {
        this.minmoney = minmoney;
    }

    public BigDecimal getMaxmoney() {
        return maxmoney;
    }

    public void setMaxmoney(BigDecimal maxmoney) {
        this.maxmoney = maxmoney;
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

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
