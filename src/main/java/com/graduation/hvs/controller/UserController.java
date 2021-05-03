package com.graduation.hvs.controller;

import com.graduation.hvs.dao.User;
import com.graduation.hvs.service.UserService;
import com.graduation.hvs.utils.DateUtils;
import com.graduation.hvs.utils.EtityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    @RequestMapping("/listUser")
    public List<Map<String, Object>> listUser() throws Exception {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            List<User> users = userService.queryAllUser();
            for (User user : users) {
                result.add(EtityUtils.entityToMap(user));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/getUserById")
    public User getUserById(@RequestBody User user) throws Exception {
        Integer userid = user.getUserid();
        if(userid == null) {
            return null;
        } else {
            return userService.getUserById(userid);
        }
    }

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody User user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String, Object> result = new HashMap<>();
        if (userService.login(username,password) == null) {
            result.put("msg", "用户名或密码错误");
            return result;
        } else {
            result.put("success", true);
            result.put("userid", userService.login(username,password));
            return result;
        }
    }

    @RequestMapping("/regist")
    public Map<String, Object> regist(@RequestBody User user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        Integer usertype = user.getUsertype();
        Map<String, Object> result = new HashMap<>();
        if (userService.hasUsername(username).equals(0)) {
            userService.regist(username,password,usertype);
            result.put("success", true);
            return result;
        } else {
            result.put("msg", "用户名已被使用");
            return result;
        }
    }

    @RequestMapping("/updateUser")
    public Map<String, Object> updateUser(@RequestBody User user) throws Exception {
        Integer userid = user.getUserid();
        String username = user.getUsername();
        String name = user.getName();
        String password = user.getPassword();
        String telephone = user.getTelephone();
        String email = user.getEmail();
        String sex = user.getSex();
        Map<String, Object> result = new HashMap<>();
        User user1 = userService.getUserById(userid);
        if (user1.getUsername().equals(username)) {
            userService.updateUser(userid,username,name,password,telephone,email,sex);
            result.put("success", true);
            result.putAll(EtityUtils.entityToMap(userService.getUserById(userid)));
            return result;
        } else if (userService.hasUsername(username).equals(0)) {
            userService.updateUser(userid,username,name,password,telephone,email,sex);
            result.put("success", true);
            result.putAll(EtityUtils.entityToMap(userService.getUserById(userid)));
            return result;
        } else {
            result.put("msg", "用户名已被使用");
            return result;
        }
    }

    @RequestMapping("/getDoctorDetail")
    public List<Map<String, Object>> getDoctorDetail() throws Exception {
        List<Map<String, Object>> list = userService.getDoctorDetail();
        for (Map<String, Object> map:list) {
            map.put("date", DateUtils.D2NYR(map.get("date")));
        }
        return list;
    }

    @RequestMapping("/addDoctorDetail")
    public Map<String, Object> addDoctorDetail(@RequestBody Map<String, String> params) throws Exception {
        String username = params.get("username");
        String name = params.get("name");
        String password = params.get("password");
        String telephone = params.get("telephone");
        String email = params.get("email");
        String sex = params.get("sex");
        String usertype = params.get("usertype");
        String department = params.get("department");
        String level = params.get("level");
        String advantage = params.get("advantage");
        String age = params.get("age");
        Map<String, Object> result = new HashMap<>();
        if (username.isEmpty() || password.isEmpty() || usertype.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (userService.hasUsername(username)>0) {
            result.put("msg", "用户名已被使用");
            return result;
        }
        userService.addDoctorDetail(username,name,password,telephone,email,sex,Integer.valueOf(usertype)
                ,department,age,level,advantage);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateDoctorDetail")
    public Map<String, Object> updateDoctorDetail(@RequestBody Map<String, String> params) throws Exception {
        String username = params.get("username");
        String name = params.get("name");
        String password = params.get("password");
        String telephone = params.get("telephone");
        String email = params.get("email");
        String sex = params.get("sex");
        String usertype = params.get("usertype");
        String department = params.get("department");
        String level = params.get("level");
        String advantage = params.get("advantage");
        String age = params.get("age");
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (username.isEmpty() || password.isEmpty() || usertype.isEmpty() || userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user1 = userService.getUserById(Integer.valueOf(userid));
        if (user1.getUsername().equals(username)) {
            userService.updateDoctorDetail(username,name,password,telephone,email,sex,Integer.valueOf(usertype)
                    ,department,age,level,advantage,Integer.valueOf(userid));
            result.put("success", true);
            return result;
        } else if (userService.hasUsername(username).equals(0)) {
            userService.updateDoctorDetail(username,name,password,telephone,email,sex,Integer.valueOf(usertype)
                    ,department,age,level,advantage,Integer.valueOf(userid));
            result.put("success", true);
            return result;
        } else {
            result.put("msg", "用户名已被使用");
            return result;
        }
    }

    @RequestMapping("/deleteDoctorDetail")
    public Map<String, Object> deleteDoctorDetail(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        userService.deleteDoctorDetail(Integer.valueOf(userid));
        result.put("success", true);
        return result;
    }
}
