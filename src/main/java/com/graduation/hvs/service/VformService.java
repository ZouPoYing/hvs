package com.graduation.hvs.service;

import com.graduation.hvs.mapper.VformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class VformService {

    @Autowired(required = false)
    private VformMapper vformMapper;

    public void addVform(Integer patient, Integer doctor, String disease, String cause, String mediid, BigDecimal money,
                         String sm, String tip) throws Exception {
        vformMapper.addVfrom(patient,doctor,disease,cause,mediid,money,sm,tip);
    }

    public Integer selectVformid() throws Exception {
        return vformMapper.selectVformid();
    }

    public List<Map<String, Object>> getMyVform(Integer userid) throws Exception {
        return vformMapper.getMyVform(userid);
    }

    public String getMedinames(String mediid) throws Exception {
        return vformMapper.getMedinames(mediid);
    }

    public List<Map<String, Object>> getMyVform1(Integer userid) throws Exception {
        return vformMapper.getMyVform1(userid);
    }
}
