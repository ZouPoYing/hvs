package com.graduation.hvs.service;

import com.graduation.hvs.dao.Set;
import com.graduation.hvs.mapper.SetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SetService {

    @Autowired(required = false)
    private SetMapper setMapper;

    public List<Set> queryAllSet() throws Exception {
        return setMapper.queryAllSet();
    }

    public void addSet(String filename, Integer fileid) throws Exception {
        setMapper.addSet(filename,fileid);
    }

    public Set getSetByUsertype(Integer usertype) throws Exception {
        return setMapper.getSetByUsertype(usertype);
    }

    public void deleteById(Integer id) throws Exception {
        setMapper.deleteById(id);
    }

    public void updateSet(String filename, Integer px, Integer id) throws Exception {
        setMapper.updateSet(filename, px, id);
    }

    public Set selectById(Integer id) throws Exception {
        return setMapper.selectById(id);
    }

    public void updateOtherSet(Integer id) throws Exception {
        setMapper.updateOtherSet(id);
    }

    public Map<String, Object> getSetFile() throws Exception {
        return setMapper.getSetFile();
    }
}
