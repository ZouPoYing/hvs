package com.graduation.hvs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Vform {

    private Integer vformid;
    private Integer patient;
    private Integer doctor;
    private String disease;
    private String cause;
    private String mediid;
    private BigDecimal money;
    private String sm;
    private String tip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    @Override
    public String toString() {
        return "Vform{" +
                "vformid=" + vformid +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", disease='" + disease + '\'' +
                ", cause='" + cause + '\'' +
                ", mediid='" + mediid + '\'' +
                ", money=" + money +
                ", sm='" + sm + '\'' +
                ", tip='" + tip + '\'' +
                ", date=" + date +
                '}';
    }

    public void setMediid(String mediid) {
        this.mediid = mediid;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Integer getVformid() {
        return vformid;
    }

    public void setVformid(Integer vformid) {
        this.vformid = vformid;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getDoctor() {
        return doctor;
    }

    public void setDoctor(Integer doctor) {
        this.doctor = doctor;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
