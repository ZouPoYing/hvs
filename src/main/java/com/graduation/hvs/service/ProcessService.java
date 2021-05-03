package com.graduation.hvs.service;

import com.graduation.hvs.mapper.ProcessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProcessService {

    @Autowired(required = false)
    private ProcessMapper processMapper;

    public void addDoctorDetail(Integer patient, Integer doctor, Integer filesid) throws Exception {
        processMapper.addDoctorDetail(patient,doctor,filesid);
    }

    public Integer hasProcessing(Integer patient) throws Exception {
        return processMapper.hasProcessing(patient);
    }

    public Integer selectProcessid() throws Exception {
        return processMapper.selectProcessid();
    }

    public Integer hasReceptioning(Integer doctor) throws Exception {
        return processMapper.hasReceptioning(doctor);
    }

    public void reception(Integer processid) throws Exception {
        processMapper.reception(processid);
    }

    public Map<String, Object> getReception(Integer doctor) throws Exception {
        return processMapper.getReception(doctor).get(0);
    }
}
