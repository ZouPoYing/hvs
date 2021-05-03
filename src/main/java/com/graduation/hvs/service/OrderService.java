package com.graduation.hvs.service;

import com.graduation.hvs.dao.Order;
import com.graduation.hvs.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    public List<Order> queryAllOrder() throws Exception {
        return orderMapper.queryAllOrder();
    }

    public void addOrder(String ordername, String technology, BigDecimal minmoney, Date enddate, Integer committerid) throws Exception {
        orderMapper.addOrder(ordername,technology,minmoney,enddate,committerid);
    }

    public Order queryLatest() throws Exception {
        return orderMapper.queryAllLatest().get(0);
    }

    public List<Map<String, Object>> getOrderAudittype2(Integer committerid) throws Exception {
        return orderMapper.getOrderAudittype2(committerid);
    }

    public List<Map<String, Object>> queryAudit2ByAudittype() throws Exception {
        return orderMapper.queryAudit2ByAudittype();
    }

    public void UpdateOrderAudit(Integer auditid,Integer auditerid,Integer orderid) throws Exception {
        orderMapper.UpdateOrderAudit(auditid,auditerid,orderid);
    }

    public Order getOrderByOrderid(Integer orderid) throws Exception {
        return orderMapper.getOrderByOrderid(orderid);
    }

    public Map<String, Object> getOrderAndFileByOrderid(Integer orderid) throws Exception {
        return orderMapper.getOrderAndFileByOrderid(orderid);
    }

    public void UpdateOrder(String ordername,String technology,BigDecimal minmoney,Date enddate, Integer orderid) throws Exception {
        orderMapper.UpdateOrder(ordername,technology,minmoney,enddate,orderid);
    }

    public List<Order> getBiddingList() throws Exception {
        return orderMapper.getBiddingList();
    }

    public List<Map<String, Object>> getApplicant(Integer orderid) throws Exception {
        return orderMapper.getApplicant(orderid);
    }

    public void UpdateOrderMaxmoney(BigDecimal money,Integer orderid) throws Exception {
        orderMapper.UpdateOrderMaxmoney(money,orderid);
    }

    public int isMyOrder(Integer orderid, Integer userid) throws Exception {
        return orderMapper.isMyOrder(orderid,userid);
    }

    public List<Map<String, Object>> getBidder(Integer orderid) throws Exception {
        return orderMapper.getBidder(orderid);
    }

    public List<Map<String, Object>> getMyTimeOutOrder(Integer userid) throws Exception {
        return orderMapper.getMyTimeOutOrder(userid);
    }

    public void UpdateOrderAudittypeAndAuditid(Integer auditid,Integer accepterid,Integer orderid) throws Exception {
        orderMapper.UpdateOrderAudittypeAndAuditid(auditid,accepterid,orderid);
    }

    public void UpdateOrderAudit24(Integer accepterid,Integer orderid) throws Exception {
        orderMapper.UpdateOrderAudit24(accepterid,orderid);
    }

    public void UpdateOrderAudit242(Integer orderid) throws Exception {
        orderMapper.UpdateOrderAudit242(orderid);
    }

    public List<Map<String, Object>> getEvaluate(Integer committerid) throws Exception {
        return orderMapper.getEvaluate(committerid);
    }

    public void UpdateOrderAudit25(Integer orderid) throws Exception {
        orderMapper.UpdateOrderAudit25(orderid);
    }
}
