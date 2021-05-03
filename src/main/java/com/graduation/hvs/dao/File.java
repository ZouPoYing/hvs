package com.graduation.hvs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class File {
    private Integer fileid;
    private String filename;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private Integer userid;

    @Override
    public String toString() {
        return "File{" +
                "fileid=" + fileid +
                ", filename='" + filename + '\'' +
                ", date=" + date +
                ", userid=" + userid +
                '}';
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
