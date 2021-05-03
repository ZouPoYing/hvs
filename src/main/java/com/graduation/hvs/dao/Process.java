package com.graduation.hvs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Process {

    private Integer processid;
    private Integer patient;
    private Integer doctor;
    private Integer filesid;
    private String processstep;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    @Override
    public String toString() {
        return "Process{" +
                "processid=" + processid +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", filesid=" + filesid +
                ", processstep='" + processstep + '\'' +
                ", date=" + date +
                '}';
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
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

    public Integer getFilesid() {
        return filesid;
    }

    public void setFilesid(Integer filesid) {
        this.filesid = filesid;
    }

    public String getProcessstep() {
        return processstep;
    }

    public void setProcessstep(String processstep) {
        this.processstep = processstep;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
