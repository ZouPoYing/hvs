package com.graduation.hvs.controller;

import com.graduation.hvs.dao.User;
import com.graduation.hvs.service.OfferService;
import com.graduation.hvs.service.OrderService;
import com.graduation.hvs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offer")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/addOffer")
    public Map<String, Object> addOffer(@RequestBody Map<String, String> params) throws Exception {
        String orderid = params.get("orderid");
        String userid = params.get("userid");
        String money = params.get("money");
        String type = params.get("type");
        Map<String, Object> result = new HashMap<>();
        if (orderid.isEmpty() || userid.isEmpty() || money.isEmpty() || type.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user = userService.getUserById(Integer.valueOf(userid));
        if (user.getUsertype() != 2) {
            result.put("msg", "您不能参与竞拍，只有供应商用户才可以竞拍");
            return result;
        }
        BigDecimal money1 = new BigDecimal(money);
        offerService.addOffer(Integer.valueOf(orderid),Integer.valueOf(userid),money1);
        if (type.equals("1")) {
            orderService.UpdateOrderMaxmoney(money1,Integer.valueOf(orderid));
        }
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getOfferByOrderid")
    public List<Map<String, Object>> getOfferByOrderid(@RequestBody Map<String, String> params) throws Exception {
        String orderid = params.get("orderid");
        if (orderid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = offerService.queryOfferByOrderid(Integer.valueOf(orderid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map:list) {
            map.put("date",sj.format(map.get("date")));
        }
        return list;
    }

    @RequestMapping("/queryOneOfferByOrderid")
    public List<Map<String, Object>> queryOneOfferByOrderid(@RequestBody Map<String, String> params) throws Exception {
        String orderid = params.get("orderid");
        if (orderid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = offerService.queryOneOfferByOrderid(Integer.valueOf(orderid));
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (Map<String, Object> map:list) {
            map.put("date",sj.format(map.get("date")));
        }
        return list;
    }

}
