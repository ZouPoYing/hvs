package com.graduation.hvs.service;

import com.graduation.hvs.mapper.MediMapper;
import com.graduation.hvs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class MediService {

    @Autowired(required = false)
    private MediMapper mediMapper;

    public void addMedi(String mediname, String meditype, String medimsg, Integer num, BigDecimal money) throws Exception {
        mediMapper.addMedi(mediname,meditype,medimsg,num,money);
    }

    public List<Map<String, Object>> getMediList() throws Exception {
        return mediMapper.getMediList();
    }

    public void updateMedi(String mediname, String meditype, String medimsg, Integer num, BigDecimal money, Integer mediid) throws Exception {
        mediMapper.updateMedi(mediname,meditype,medimsg,num,money,mediid);
    }

    public void deleteMedi(Integer mediid) throws Exception {
        mediMapper.deleteMedi(mediid);
    }
}
