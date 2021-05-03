package com.graduation.hvs.controller;

import com.graduation.hvs.dao.Order;
import com.graduation.hvs.service.AuditService;
import com.graduation.hvs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuditService auditService;

    @RequestMapping("/addOrder")
    public Map<String, Object> addOrder(@RequestBody Map<String, String> params) throws Exception {
        String ordername = params.get("ordername");
        String technology = params.get("technology");
        String minmoney = params.get("minmoney");
        String enddate = params.get("enddate");
        String fileid = params.get("fileid");
        String committerid = params.get("committerid");
        Map<String, Object> result = new HashMap<>();
        if (ordername.isEmpty() || technology.isEmpty() || fileid.isEmpty() || minmoney.isEmpty() || committerid.isEmpty() || enddate.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        String subdate = enddate.replaceFirst(enddate.substring(10,11)," ").substring(0,19);
        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newenddate = sj.parse(subdate);
        BigDecimal money = new BigDecimal(minmoney);
        orderService.addOrder(ordername,technology,money,newenddate,Integer.valueOf(committerid));
        Order order = orderService.queryLatest();
        auditService.addAuditType2(Integer.valueOf(committerid),Integer.valueOf(fileid),order.getOrderid());
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getOrder")
    public List<Map<String, Object>> getOrder(@RequestBody Map<String, String> params) throws Exception {
        String committerid = params.get("committerid");
        if (committerid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = orderService.getOrderAudittype2(Integer.valueOf(committerid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map:list) {
            map.put("date", sj.format(map.get("date")));
            map.put("name", map.get("filename").toString().substring(map.get("filename").toString().replaceFirst("_","-").indexOf("_")+1));
        }
        return list;
    }

    @RequestMapping("/queryAudit2ByAudittype")
    public List<Map<String, Object>> queryAudit2ByAudittype(@RequestBody Map<String, String> params) throws Exception {
        String audittype = params.get("audittype");
        String auditerid = params.get("auditerid");
        if (!audittype.equals("2") || auditerid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = orderService.queryAudit2ByAudittype();
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map:list) {
            map.put("date", sj.format(map.get("date")));
            map.put("name", map.get("filename").toString().substring(map.get("filename").toString().replaceFirst("_","-").indexOf("_")+1));
        }
        return list;
    }

    @RequestMapping("/modifyOrder")
    public Map<String, Object> modifyOrder(@RequestBody Map<String, String> params) throws Exception {
        String ordername = params.get("ordername");
        String technology = params.get("technology");
        String minmoney = params.get("minmoney");
        String enddate = params.get("enddate");
        String fileid = params.get("fileid");
        String committerid = params.get("committerid");
        String orderid = params.get("orderid");
        Map<String, Object> result = new HashMap<>();
        if (ordername.isEmpty() || technology.isEmpty() || minmoney.isEmpty() || committerid.isEmpty() || orderid.isEmpty() || enddate.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        String subdate = enddate.replaceFirst(enddate.substring(10,11)," ").substring(0,19);
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date newenddate = sj.parse(subdate);
        Order order = orderService.getOrderByOrderid(Integer.valueOf(orderid));
        if (fileid.isEmpty()) {
            auditService.modifyAuditType2NoFileid(order.getAuditid());
        } else {
            auditService.modifyAuditType2(Integer.valueOf(fileid),order.getAuditid());
        }
        BigDecimal money = new BigDecimal(minmoney);
        orderService.UpdateOrder(ordername,technology,money,newenddate,Integer.valueOf(orderid));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getBiddingList")
    public List<Order> getBiddingList() throws Exception {
        return orderService.getBiddingList();
    }

    @RequestMapping("/getOrderByOrderid")
    public Order getOrderByOrderid(@RequestBody Map<String, String> params) throws Exception {
        String orderid = params.get("orderid");
        if (orderid.isEmpty()) {
            return null;
        }
        return orderService.getOrderByOrderid(Integer.valueOf(orderid));
    }

    @RequestMapping("/getOrderAndFileByOrderid")
    public Map<String, Object> getOrderAndFileByOrderid(@RequestBody Map<String, String> params) throws Exception {
        String orderid = params.get("orderid");
        if (orderid.isEmpty()) {
            return null;
        }
        Map<String, Object> map = orderService.getOrderAndFileByOrderid(Integer.valueOf(orderid));
        map.put("name", map.get("filename").toString().substring(map.get("filename").toString().replaceFirst("_","-").indexOf("_")+1));
        return map;
    }

    @RequestMapping("/getApplicant")
    public List<Map<String, Object>> getApplicant(@RequestBody Map<String, String> params) throws Exception {
        String orderid = params.get("orderid");
        if (orderid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = orderService.getApplicant(Integer.valueOf(orderid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map:list) {
            map.put("date", sj.format(map.get("date")));
            map.put("name", map.get("filename").toString().substring(map.get("filename").toString().replaceFirst("_","-").indexOf("_")+1));
        }
        return list;
    }

    @RequestMapping("/isMyOrder")
    public Map<String, Object> isMyOrder(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        String orderid = params.get("orderid");
        Map<String, Object> result = new HashMap<>();
        if (userid.isEmpty() || orderid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (orderService.isMyOrder(Integer.valueOf(orderid),Integer.valueOf(userid)) == 0) {
            result.put("msg", "您无权查看供应商信息");
            return result;
        }
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getBidder")
    public List<Map<String, Object>> getBidder(@RequestBody Map<String, String> params) throws Exception {
        String orderid = params.get("orderid");
        if (orderid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = orderService.getBidder(Integer.valueOf(orderid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map:list) {
            if (map.get("telephone")==null) {
                map.put("telephone", "无");
            }
            if (map.get("email")==null) {
                map.put("email", "无");
            }
            map.put("date", sj.format(map.get("date")));
            map.put("name", map.get("filename").toString().substring(map.get("filename").toString().replaceFirst("_","-").indexOf("_")+1));
        }
        return list;
    }

    @RequestMapping("/getMyTimeOutOrder")
    public List<Map<String, Object>> getMyTimeOutOrder(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = orderService.getMyTimeOutOrder(Integer.valueOf(userid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map:list) {
            if (map.get("filename")==null) {
                map.put("filename", "无");
                map.put("name", "无");
            } else {
                map.put("name", map.get("filename").toString().substring(map.get("filename").toString().replaceFirst("_","-").indexOf("_")+1));
            }
            map.put("date", sj.format(map.get("date")));
        }
        return list;
    }

    @RequestMapping("/endOrder")
    public Map<String, Object> endOrder(@RequestBody Map<String, String> params) throws Exception {
        String orerid = params.get("orerid");
        String supplierid = params.get("supplierid");
        Map<String, Object> result = new HashMap<>();
        if (orerid.isEmpty() || supplierid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return null;
        }
        orderService.UpdateOrderAudit24(Integer.valueOf(supplierid),Integer.valueOf(orerid));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getEvaluate")
    public List<Map<String, Object>> getEvaluate(@RequestBody Map<String, String> params) throws Exception {
        String committerid = params.get("committerid");
        if (committerid.isEmpty()) {
            return null;
        }
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        List<Map<String, Object>> list = orderService.getEvaluate(Integer.valueOf(committerid));
        for (Map<String, Object> map:list) {
            map.put("date", sj.format(map.get("date")));
            map.put("enddate", sj.format(map.get("enddate")));
        }
        return list;
    }
}
