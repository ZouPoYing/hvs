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
                           String email, String sex) throws Exception {
        userMapper.updateUser(userid,username,name,password,telephone,email,sex);
    }

    public void updateAuditByUserid(Integer userid, Integer audit) throws Exception {
        userMapper.updateAuditByUserid(userid,audit);
    }

    public List<Map<String, Object>> getDoctorDetail() throws Exception {
        return userMapper.getDoctorDetail();
    }

    public void addDoctorDetail(String username, String name, String password, String telephone,
                                String email, String sex, Integer usertype, String department,
                                String age, String level, String advantage) throws Exception {
        userMapper.addDoctorDetail(username,name,password,telephone,email,sex,usertype,department,
                age,level,advantage);
    }

    public void updateDoctorDetail(String username, String name, String password, String telephone,
                            String email, String sex, Integer usertype, String department,
                            String age, String level, String advantage, Integer userid) throws Exception {
        userMapper.updateDoctorDetail(username,name,password,telephone,email,sex,usertype,department,
                age,level,advantage,userid);
    }

    public void deleteDoctorDetail(Integer userid) throws Exception {
        userMapper.deleteDoctorDetail(userid);
    }
}
