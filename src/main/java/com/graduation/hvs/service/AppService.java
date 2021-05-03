package com.graduation.hvs.service;

import com.graduation.hvs.dao.App;
import com.graduation.hvs.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired(required = false)
    private AppMapper appMapper;

    public List<App> queryAllApp() throws Exception {
        return appMapper.queryAllApp();
    }

    public List<App> queryAppListByType(Integer type) throws Exception {
        return appMapper.queryAppListByType(type);
    }
}
