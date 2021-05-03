package com.graduation.hvs.service;

import com.graduation.hvs.dao.Offer;
import com.graduation.hvs.mapper.OfferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OfferService {

    @Autowired(required = false)
    private OfferMapper offerMapper;

    public List<Offer> queryAllOffer() throws Exception {
        return offerMapper.queryAllOffer();
    }

    public void addOffer(Integer orderid, Integer userid, BigDecimal money) throws Exception {
        offerMapper.addOffer(orderid,userid,money);
    }

    public List<Map<String, Object>> queryOfferByOrderid(Integer orderid) throws Exception {
        return offerMapper.queryOfferByOrderid(orderid);
    }

    public List<Map<String, Object>> queryOneOfferByOrderid(Integer orderid) throws Exception {
        return offerMapper.queryOneOfferByOrderid(orderid);
    }
}
