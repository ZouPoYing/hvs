package com.graduation.hvs.controller;

import com.graduation.hvs.dao.App;
import com.graduation.hvs.service.AppService;
import com.graduation.hvs.utils.EtityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class AppController {


    @Resource
    private AppService appService;

    @RequestMapping("/listApp")
    public List<Map<String, Object>> listApp() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            List<App> apps = appService.queryAllApp();
            for (App app : apps) {
                result.add(EtityUtils.entityToMap(app));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/getAppByType")
    public List<App> getAppByType(@RequestBody App app) throws Exception {
        Integer type = app.getType();
        if (type == null) {
            type = -1;
        }
        return appService.queryAppListByType(type);
    }
}
