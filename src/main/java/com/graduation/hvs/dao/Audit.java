package com.graduation.hvs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Audit {
    private Integer auditid;
    private Integer audittype;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private Integer committerid;
    private Integer auditerid;
    private Integer fileid;
    private String company;
    private String address;
    private Integer orderid;
    private String state;
    private String reason;

    @Override
    public String toString() {
        return "Audit{" +
                "auditid=" + auditid +
                ", audittype=" + audittype +
                ", date=" + date +
                ", committerid=" + committerid +
                ", auditerid=" + auditerid +
                ", fileid=" + fileid +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", orderid=" + orderid +
                ", state='" + state + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    public Integer getAudittype() {
        return audittype;
    }

    public void setAudittype(Integer audittype) {
        this.audittype = audittype;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCommitterid() {
        return committerid;
    }

    public void setCommitterid(Integer committerid) {
        this.committerid = committerid;
    }

    public Integer getAuditerid() {
        return auditerid;
    }

    public void setAuditerid(Integer auditerid) {
        this.auditerid = auditerid;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
