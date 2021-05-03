package com.graduation.hvs.controller;

import com.graduation.hvs.dao.Audit;
import com.graduation.hvs.dao.File;
import com.graduation.hvs.dao.Order;
import com.graduation.hvs.dao.User;
import com.graduation.hvs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/audit")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/addAuditType1")
    public Map<String, Object> addAuditType1(@RequestBody Map<String, String> params) throws Exception {
        String audittype = params.get("audittype");
        String committerid = params.get("committerid");
        String fileid = params.get("fileid");
        String company = params.get("company");
        String address = params.get("address");
        Map<String, Object> result = new HashMap<>();
        if (audittype.isEmpty() || committerid.isEmpty() || fileid.isEmpty() || company.isEmpty() || address.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        auditService.addAuditType1(Integer.valueOf(audittype),Integer.valueOf(committerid),
                Integer.valueOf(fileid),company,address);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/queryAuditByAudittype")
    public List<Map<String, Object>> queryAuditByAudittype(@RequestBody Map<String, String> params) throws Exception {
        String audittype = params.get("audittype");
        if (audittype.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> results = new ArrayList<>();
        List<Audit> audits = auditService.queryAuditByAudittype(Integer.valueOf(audittype));
        for ( Audit audit : audits) {
            Map<String, Object> result = new HashMap<>();
            User user = userService.getUserById(audit.getCommitterid());
            File file = fileService.getFileByFileid(audit.getFileid());
            result.put("username", user.getUsername());
            result.put("usertype", user.getUsertype());
            result.put("telephone", user.getTelephone());
            result.put("email", user.getEmail());
            result.put("filename", file.getFilename());
            result.put("name", file.getFilename().substring(file.getFilename().replaceFirst("_","-").indexOf("_")+1));
            result.put("fileid", file.getFileid());
            SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            result.put("date", sj.format(audit.getDate()));
            result.put("company", audit.getCompany());
            result.put("address", audit.getAddress());
            result.put("state", audit.getState());
            result.put("auditid", audit.getAuditid());
            results.add(result);
        }
        return results;
    }

    @RequestMapping("/AuditType1")
    public Map<String, Object> AuditType1(@RequestBody Map<String, String> params) throws Exception {
        String msgtype = params.get("msgtype");
        String msg = params.get("msg");
        String auditid = params.get("auditid");
        String auditerid = params.get("auditerid");
        Map<String, Object> result = new HashMap<>();
        if (msgtype.isEmpty() || msg.isEmpty() || auditid.isEmpty() || auditerid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (msgtype.equals("0")) {
            auditService.AuditType1(Integer.valueOf(auditid),Integer.valueOf(auditerid),"退回");
        } else {
            auditService.AuditType1(Integer.valueOf(auditid),Integer.valueOf(auditerid),"通过");
            Audit audit = auditService.getAuditByAuditid(Integer.valueOf(auditid));
            userService.updateAuditByUserid(audit.getCommitterid(), 1);
        }
        msgService.addMsg(Integer.valueOf(auditid),msg,Integer.valueOf(msgtype));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/checkAuditType1")
    public Map<String, Object> checkAuditType1(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (auditService.checkAuditType1(Integer.valueOf(userid)) == null) {
            result.put("success", true);
            return result;
        }
        result.putAll(auditService.checkAuditType1(Integer.valueOf(userid)));
        if (result.get("state").equals("待审核") || result.get("state").equals("通过")) {
            result.put("msg", "state为待审核或通过");
            return result;
        } else {
            result.put("success", true);
            return result;
        }
    }

    @RequestMapping("/modifyAuditType1")
    public Map<String, Object> modifyAuditType1(@RequestBody Map<String, String> params) throws Exception {
        String auditid = params.get("auditid");
        String committerid = params.get("committerid");
        String fileid = params.get("fileid");
        String company = params.get("company");
        String address = params.get("address");
        Map<String, Object> result = new HashMap<>();
        if (auditid.isEmpty() || committerid.isEmpty() || company.isEmpty() || address.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (fileid.isEmpty()) {
            auditService.modifyAuditType1NoFileid(Integer.valueOf(auditid),company,address);
        } else {
            auditService.modifyAuditType1(Integer.valueOf(fileid),Integer.valueOf(auditid),company,address);
        }
        result.put("success", true);
        return result;
    }

    @RequestMapping("/AuditType2")
    public Map<String, Object> AuditType2(@RequestBody Map<String, String> params) throws Exception {
        String msgtype = params.get("msgtype");
        String msg = params.get("msg");
        String auditid = params.get("auditid");
        String auditerid = params.get("auditerid");
        String orderid = params.get("orderid");
        Map<String, Object> result = new HashMap<>();
        if (msgtype.isEmpty() || msg.isEmpty() || auditid.isEmpty() || auditerid.isEmpty() || orderid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        orderService.UpdateOrderAudit(Integer.valueOf(auditid),Integer.valueOf(auditerid),Integer.valueOf(orderid));
        auditService.AuditType2(Integer.valueOf(auditid),Integer.valueOf(auditerid),msgtype.equals("0")?"退回":"通过");
        msgService.addMsg(Integer.valueOf(auditid),msg,Integer.valueOf(msgtype));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/AuditType3")
    public Map<String, Object> AuditType3(@RequestBody Map<String, String> params) throws Exception {
        String msgtype = params.get("msgtype");
        String msg = params.get("msg");
        String auditid = params.get("auditid");
        String auditerid = params.get("auditerid");
        String orderid = params.get("orderid");
        Map<String, Object> result = new HashMap<>();
        if (msgtype.isEmpty() || msg.isEmpty() || auditid.isEmpty() || auditerid.isEmpty() || orderid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (msgtype.equals("1")) {
            orderService.UpdateOrderAudit242(Integer.valueOf(orderid));
        }
        auditService.AuditType2(Integer.valueOf(auditid),Integer.valueOf(auditerid),msgtype.equals("0")?"退回":"通过");
        msgService.addMsg(Integer.valueOf(auditid),msg,Integer.valueOf(msgtype));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/addAudit3")
    public Map<String, Object> addAudit3(@RequestBody Map<String, String> params) throws Exception {
        String supplierid = params.get("supplierid");
        String reason = params.get("reason");
        String orderid = params.get("orderid");
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (supplierid.isEmpty() || reason.isEmpty() || orderid.isEmpty() || userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Order order = orderService.getOrderByOrderid(Integer.valueOf(orderid));
        if (order.getAudittype()==2) {
            auditService.addAuditType3(Integer.valueOf(userid),Integer.valueOf(orderid),reason);
            Audit audit = auditService.getCurrentAudit();
            orderService.UpdateOrderAudittypeAndAuditid(audit.getAuditid(),Integer.valueOf(supplierid),Integer.valueOf(orderid));
        } else {
            auditService.updateAuditStateAndReason(reason,order.getAuditid());
        }
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getAuditType3")
    public List<Map<String, Object>> getAuditType3() throws Exception {
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        List<Map<String, Object>> list = auditService.getAuditType3();
        for (Map<String, Object> map:list) {
            map.put("date",sj.format(map.get("date")));
        }
        return list;
    }

    @RequestMapping("/AuditType5")
    public Map<String, Object> AuditType5(@RequestBody Map<String, String> params) throws Exception {
        String msg = params.get("msg");
        String userid = params.get("userid");
        String orderid = params.get("orderid");
        Map<String, Object> result = new HashMap<>();
        if (msg.isEmpty() || orderid.isEmpty() || userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        orderService.UpdateOrderAudit25(Integer.valueOf(orderid));
        msgService.addMsg2(Integer.valueOf(userid),msg,Integer.valueOf(orderid));
        result.put("success", true);
        return result;
    }
}
