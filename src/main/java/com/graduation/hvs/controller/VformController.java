package com.graduation.hvs.controller;

import com.graduation.hvs.service.MsgService;
import com.graduation.hvs.service.ProcessService;
import com.graduation.hvs.service.VformService;
import com.graduation.hvs.utils.DateUtils;
import org.apache.ibatis.annotations.Mapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vform")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class VformController {

    @Autowired
    private VformService vformService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private MsgService msgService;

    @RequestMapping("/addVform")
    public Map<String, Object> addVform(@RequestBody Map<String, String> params) throws Exception {
        String doctor = params.get("doctor");
        String patient = params.get("patient");
        String disease = params.get("disease");
        String cause = params.get("cause");
        String mediid = params.get("mediid");
        String money = params.get("money");
        String sm = params.get("sm");
        String tip = params.get("tip");
        String processid = params.get("processid");
        Map<String, Object> result = new HashMap<>();
        if (doctor.isEmpty() || patient.isEmpty() || disease.isEmpty() || cause.isEmpty() || mediid.isEmpty() ||
                money.isEmpty() || sm.isEmpty() || tip.isEmpty() || processid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        vformService.addVform(Integer.valueOf(patient),Integer.valueOf(doctor),disease,cause,mediid,
                new BigDecimal(money),sm,tip);
        processService.updateProcessstepByProcessid("缴费",vformService.selectVformid(),Integer.valueOf(processid));
        String msg = "你好，医生已经为你开出了就诊单，请先缴费，然后取药";
        msgService.addMsgHvs(msg,13,Integer.valueOf(processid),Integer.valueOf(patient),Integer.valueOf(doctor));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getMyVform")
    public List<Map<String, Object>> getMyVform(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> vformList = vformService.getMyVform(Integer.valueOf(userid));
        for (Map<String, Object> map : vformList) {
            String m = map.get("mediid").toString().replace("[","")
                    .replace("]","");
            map.put("medinames",vformService.getMedinames(m));
            map.put("date", DateUtils.D2NYR(map.get("date")));
        }
        return vformList;
    }

    @RequestMapping("/getMyVform1")
    public List<Map<String, Object>> getMyVform1(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> vformList = vformService.getMyVform1(Integer.valueOf(userid));
        for (Map<String, Object> map : vformList) {
            String m = map.get("mediid").toString().replace("[","")
                    .replace("]","");
            map.put("medinames",vformService.getMedinames(m));
            map.put("date", DateUtils.D2NYR(map.get("date")));
        }
        return vformList;
    }
}
