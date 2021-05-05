package com.graduation.hvs.controller;

import com.graduation.hvs.dao.Msg;
import com.graduation.hvs.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/msg")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class MsgController {

    @Autowired
    private MsgService msgService;

    public List<Msg> queryAllMsg() throws Exception {
        return msgService.queryAllMsg();
    }

    @RequestMapping("/queryMsg1")
    public List<Map<String, Object>> queryMsg1(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = msgService.queryMsg1(Integer.valueOf(userid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map : list) {
            map.put("date", sj.format(map.get("date")));
        }
        return list;
    }

    @RequestMapping("/queryMsg2")
    public List<Map<String, Object>> queryMsg2(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = msgService.queryMsg2(Integer.valueOf(userid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map : list) {
            map.put("date", sj.format(map.get("date")));
        }
        return list;
    }

    @RequestMapping("/UpdateMsgUse")
    public Map<String, Object> UpdateMsgUse(@RequestBody Map<String, String> params) throws Exception {
        String msgid = params.get("msgid");
        Map<String, Object> result = new HashMap<>();
        if (msgid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return null;
        }
        msgService.UpdateMsgUse(Integer.valueOf(msgid));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/queryMsg11")
    public List<Map<String, Object>> queryMsg11(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = msgService.queryMsg11(Integer.valueOf(userid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map : list) {
            map.put("date", sj.format(map.get("date")));
            map.put("title", "预约挂号");
        }
        return list;
    }

    @RequestMapping("/getReception")
    public List<Map<String, Object>> getReception(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = msgService.getReception(Integer.valueOf(userid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map : list) {
            map.put("date", sj.format(map.get("date")));
            map.put("file", map.get("filename").toString().substring(map.get("filename").toString().replaceFirst("_","-").indexOf("_")+1));
        }
        return list;
    }

    @RequestMapping("/queryMsg12")
    public List<Map<String, Object>> queryMsg12(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = msgService.queryMsg12(Integer.valueOf(userid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map : list) {
            map.put("date", sj.format(map.get("date")));
            if ((int) map.get("msgtype")==12) {
                map.put("title", "接诊消息");
            } else if ((int) map.get("msgtype")==13){
                map.put("title", "缴费提醒");
            } else if ((int) map.get("msgtype")==14){
                map.put("title", "缴费成功");
            } else {
                map.put("title", "取药成功");
            }
        }
        return list;
    }
}
