package com.graduation.hvs.dao;

public class App {
    private Integer id;
    private String name;
    private String router;
    private Integer type;

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", router='" + router + '\'' +
                ", type=" + type +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
