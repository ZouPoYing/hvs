package com.graduation.hvs.service;

import com.graduation.hvs.dao.User;
import com.graduation.hvs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    public List<User> queryAllUser() throws Exception {
        return userMapper.queryAllUser();
    };

    public User getUserById(Integer userid) throws Exception {
        return userMapper.getUserById(userid);
    };

    public Integer login(String username, String password) throws Exception {
        return userMapper.login(username,password);
    }

    public Integer hasUsername(String username) throws Exception {
        return userMapper.hasUsername(username);
    }

    public void regist(String username, String password, Integer usertype) throws Exception {
        userMapper.regist(username,password,usertype);
    }

    public void updateUser(Integer userid, String username, String name, String password, String telephone,
                           String email, String sex, String age, String address) throws Exception {
        userMapper.updateUser(userid,username,name,password,telephone,email,sex,age,address);
    }

    public void updateAuditByUserid(Integer userid, Integer audit) throws Exception {
        userMapper.updateAuditByUserid(userid,audit);
    }

    public List<Map<String, Object>> getDoctorDetail() throws Exception {
        return userMapper.getDoctorDetail();
    }

    public List<Map<String, Object>> getPDetail() throws Exception {
        return userMapper.getPDetail();
    }

    public void addDoctorDetail(String username, String name, String password, String telephone,
                                String email, String sex, Integer usertype, String department,
                                String age, String level, String advantage, String address) throws Exception {
        userMapper.addDoctorDetail(username,name,password,telephone,email,sex,usertype,department,
                age,level,advantage,address);
    }

    public void updateDoctorDetail(String username, String name, String password, String telephone,
                            String email, String sex, Integer usertype, String department,
                            String age, String level, String advantage, String address, Integer userid) throws Exception {
        userMapper.updateDoctorDetail(username,name,password,telephone,email,sex,usertype,department,
                age,level,advantage,address,userid);
    }

    public void deleteDoctorDetail(Integer userid) throws Exception {
        userMapper.deleteDoctorDetail(userid);
    }

    public Map<String, Object> getAdmin() throws Exception {
        return userMapper.getAdmin().get(0);
    }

    public List<String> getLevel() throws Exception {
        return userMapper.getLevel();
    }

    public List<String> getDepartment() throws Exception {
        return userMapper.getDepartment();
    }

    public List<Map<String, Object>> getTJDoctor(String sub) throws Exception {
        return userMapper.getTJDoctor(sub);
    }

    public List<Map<String, Object>> getStatistics(String sub) throws Exception {
        return userMapper.getStatistics(sub);
    }

    public List<Map<String, Object>> getStatisticsPie(String sub) throws Exception {
        return userMapper.getStatisticsPie(sub);
    }
}
