package com.graduation.hvs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Set {
    private Integer id;
    private String filename;
    private Integer usertype;
    private String path;
    private Integer px;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
    private Integer fileid;

    @Override
    public String toString() {
        return "Set{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", usertype=" + usertype +
                ", path='" + path + '\'' +
                ", px=" + px +
                ", date=" + date +
                ", fileid=" + fileid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }
}
