package com.graduation.hvs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Medi {

    private Integer mediid;
    private String mediname;
    private String meditype;
    private String medimsg;
    private Integer num;
    private BigDecimal money;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    @Override
    public String toString() {
        return "Medi{" +
                "mediid=" + mediid +
                ", mediname='" + mediname + '\'' +
                ", meditype='" + meditype + '\'' +
                ", medimsg='" + medimsg + '\'' +
                ", num=" + num +
                ", money=" + money +
                ", date=" + date +
                '}';
    }

    public Integer getMediid() {
        return mediid;
    }

    public void setMediid(Integer mediid) {
        this.mediid = mediid;
    }

    public String getMediname() {
        return mediname;
    }

    public void setMediname(String mediname) {
        this.mediname = mediname;
    }

    public String getMeditype() {
        return meditype;
    }

    public void setMeditype(String meditype) {
        this.meditype = meditype;
    }

    public String getMedimsg() {
        return medimsg;
    }

    public void setMedimsg(String medimsg) {
        this.medimsg = medimsg;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
