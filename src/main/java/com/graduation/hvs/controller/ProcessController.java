package com.graduation.hvs.controller;

import com.graduation.hvs.dao.User;
import com.graduation.hvs.service.MsgService;
import com.graduation.hvs.service.ProcessService;
import com.graduation.hvs.service.UserService;
import com.graduation.hvs.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/process")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private UserService userService;

    @RequestMapping("/addProcess")
    public Map<String, Object> addProcess(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        String doctor = params.get("doctor");
        String fileid = params.get("fileid");
        Map<String, Object> result = new HashMap<>();
        if (doctor.isEmpty() || userid.isEmpty() || fileid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (processService.hasProcessing(Integer.valueOf(userid))>0) {
            result.put("msg", "您有就诊流程正在进行中，无法发起新的");
            return result;
        }
        String msg = "医生你好，有病人于" + DateUtils.getNow() + "挂了你的号，请及时处理！";
        if (fileid == null || fileid == "" || fileid.isEmpty()) {
            processService.addDoctorDetail(Integer.valueOf(userid),Integer.valueOf(doctor),0);

            msgService.addMsgHvs(msg,11,processService.selectProcessid(),Integer.valueOf(userid),Integer.valueOf(doctor));
            result.put("success", true);
            return result;
        } else {
            processService.addDoctorDetail(Integer.valueOf(userid),Integer.valueOf(doctor),Integer.valueOf(fileid));
            msgService.addMsgHvs(msg,11,processService.selectProcessid(),Integer.valueOf(userid),Integer.valueOf(doctor));
            result.put("success", true);
            return result;
        }
    }

    @RequestMapping("/reception")
    public Map<String, Object> reception(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        String processid = params.get("processid");
        String patient = params.get("patient");
        Map<String, Object> result = new HashMap<>();
        if (processid.isEmpty() || userid.isEmpty() || patient.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (processService.hasReceptioning(Integer.valueOf(userid))>0) {
            result.put("msg", "您有病人正在就诊，无法接诊其他病人");
            return result;
        }
        User user = userService.getUserById(Integer.valueOf(userid));
        String msg = "你好，" + user.getName() + "医生于" + DateUtils.getNow() + "接诊了你，请前往" + user.getDepartment() + "科室就医！";
        processService.reception(Integer.valueOf(processid));
        msgService.addMsgHvs(msg,12,Integer.valueOf(processid),Integer.valueOf(patient),Integer.valueOf(userid));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getReception")
    public Map<String, Object> getReception(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (processService.hasReceptioning(Integer.valueOf(userid))==0) {
            result.put("msg", "您没有正在接诊的病人，请先接诊病人");
            return result;
        }
        result.put("success", true);
        result.putAll(processService.getReception(Integer.valueOf(userid)));
        result.put("file", result.get("filename").toString().substring(result.get("filename").toString().replaceFirst("_","-").indexOf("_")+1));
        result.put("date", DateUtils.D2NYR(result.get("date")));
        return result;
    }

    @RequestMapping("/updateFileid")
    public Map<String, Object> updateFileid(@RequestBody Map<String, String> params) throws Exception {
        String fileid = params.get("fileid");
        String processid = params.get("processid");
        Map<String, Object> result = new HashMap<>();
        if (processid.isEmpty() || fileid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        processService.updateFileid(Integer.valueOf(fileid),Integer.valueOf(processid));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateP")
    public Map<String, Object> updateP(@RequestBody Map<String, String> params) throws Exception {
        String processid = params.get("processid");
        String doctor = params.get("doctor");
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (processid.isEmpty() || doctor.isEmpty() || userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Map<String, Object> map = processService.selectProcessByProcessid(Integer.valueOf(processid));
        if (!map.get("processstep").equals("缴费")) {
            result.put("msg", "已缴费，无需再次缴费");
            return result;
        }
        processService.updatePByProcessid("取药",Integer.valueOf(processid));
        String msg = "恭喜你缴费成功，现在可以去取药了！";
        msgService.addMsgHvs(msg,14,Integer.valueOf(processid),Integer.valueOf(userid),Integer.valueOf(doctor));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateP1")
    public Map<String, Object> updateP1(@RequestBody Map<String, String> params) throws Exception {
        String processid = params.get("processid");
        String patient = params.get("patient");
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (processid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Map<String, Object> map = processService.selectProcessByProcessid(Integer.valueOf(processid));
        if (!map.get("processstep").equals("取药")) {
            result.put("msg", "已取药确认，无需再次确认");
            return result;
        }
        processService.updatePByProcessid("结束",Integer.valueOf(processid));
        String msg = "恭喜你取药成功，现在就诊流程已经全部完成，祝您早日康复！";
        msgService.addMsgHvs(msg,15,Integer.valueOf(processid),Integer.valueOf(patient),Integer.valueOf(userid));
        result.put("success", true);
        return result;
    }
}
