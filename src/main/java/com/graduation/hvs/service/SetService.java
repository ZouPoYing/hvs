package com.graduation.hvs.service;

import com.graduation.hvs.dao.Set;
import com.graduation.hvs.mapper.SetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetService {

    @Autowired(required = false)
    private SetMapper setMapper;

    public List<Set> queryAllSet() throws Exception {
        return setMapper.queryAllSet();
    }

    public void addSet(String filename, Integer usertype, Integer px, Integer fileid) throws Exception {
        setMapper.addSet(filename,usertype,px,fileid);
    }

    public Set getSetByUsertype(Integer usertype) throws Exception {
        return setMapper.getSetByUsertype(usertype);
    }
}
