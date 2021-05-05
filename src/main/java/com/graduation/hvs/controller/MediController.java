package com.graduation.hvs.controller;

import com.graduation.hvs.service.MediService;
import com.graduation.hvs.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medi")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class MediController {

    @Autowired
    private MediService mediService;

    @RequestMapping("/addMedi")
    public Map<String, Object> addMedi(@RequestBody Map<String, String> params) throws Exception {
        String mediname = params.get("mediname");
        String meditype = params.get("meditype");
        String medimsg = params.get("medimsg");
        String num = params.get("num");
        String money = params.get("money");
        Map<String, Object> result = new HashMap<>();
        if (mediname.isEmpty() || meditype.isEmpty() || medimsg.isEmpty() || num.isEmpty() || money.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        mediService.addMedi(mediname,meditype,medimsg,Integer.valueOf(num),new BigDecimal(money));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getMedi")
    public List<Map<String, Object>> getMedi() throws Exception {
        List<Map<String, Object>> mediList = mediService.getMediList();
        for (Map<String, Object> map : mediList) {
            map.put("date", DateUtils.D2NYR(map.get("date")));
        }
        return mediList;
    }

    @RequestMapping("/updateMedi")
    public Map<String, Object> updateMedi(@RequestBody Map<String, String> params) throws Exception {
        String mediname = params.get("mediname");
        String meditype = params.get("meditype");
        String medimsg = params.get("medimsg");
        String num = params.get("num");
        String money = params.get("money");
        String mediid = params.get("mediid");
        Map<String, Object> result = new HashMap<>();
        if (mediname.isEmpty() || meditype.isEmpty() || medimsg.isEmpty() || num.isEmpty() || money.isEmpty() || mediid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        mediService.updateMedi(mediname,meditype,medimsg,Integer.valueOf(num),new BigDecimal(money),Integer.valueOf(mediid));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/deleteMedi")
    public Map<String, Object> deleteMedi(@RequestBody Map<String, String> params) throws Exception {
        String mediid = params.get("mediid");
        Map<String, Object> result = new HashMap<>();
        if (mediid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        mediService.deleteMedi(Integer.valueOf(mediid));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/selectMedi")
    public List<Map<String, Object>> selectMedi() throws Exception {
        List<Map<String, Object>> mediList = mediService.getMediList();
        List<Map<String, Object>> results = new ArrayList<>();
        for (Map<String, Object> map : mediList) {
            Map<String, Object> result = new HashMap<>();
            result.put("key", map.get("mediid"));
            result.put("label", map.get("mediname")+"_"+map.get("money")+"_"+map.get("num"));
            results.add(result);
        }
        return results;
    }
}
