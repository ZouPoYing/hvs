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
            result.put("msg", "账号或密码错误");
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
            result.put("msg", "账号已被注册");
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
        String age = user.getAge();
        String address = user.getAddress();
        Map<String, Object> result = new HashMap<>();
        User user1 = userService.getUserById(userid);
        if (user1.getUsername().equals(username)) {
            userService.updateUser(userid,username,name,password,telephone,email,sex,age,address);
            result.put("success", true);
            result.putAll(EtityUtils.entityToMap(userService.getUserById(userid)));
            return result;
        } else if (userService.hasUsername(username).equals(0)) {
            userService.updateUser(userid,username,name,password,telephone,email,sex,age,address);
            result.put("success", true);
            result.putAll(EtityUtils.entityToMap(userService.getUserById(userid)));
            return result;
        } else {
            result.put("msg", "账号已被使用");
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

    @RequestMapping("/getPDetail")
    public List<Map<String, Object>> getPDetail() throws Exception {
        List<Map<String, Object>> list = userService.getPDetail();
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
        String address = params.get("address");
        Map<String, Object> result = new HashMap<>();
        if (username.isEmpty() || password.isEmpty() || usertype.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (userService.hasUsername(username)>0) {
            result.put("msg", "账号已被使用");
            return result;
        }
        userService.addDoctorDetail(username,name,password,telephone,email,sex,Integer.valueOf(usertype)
                ,department,age,level,advantage,address);
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
        String address = params.get("address");
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (username.isEmpty() || password.isEmpty() || usertype.isEmpty() || userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user1 = userService.getUserById(Integer.valueOf(userid));
        if (user1.getUsername().equals(username)) {
            userService.updateDoctorDetail(username,name,password,telephone,email,sex,Integer.valueOf(usertype)
                    ,department,age,level,advantage,address,Integer.valueOf(userid));
            result.put("success", true);
            return result;
        } else if (userService.hasUsername(username).equals(0)) {
            userService.updateDoctorDetail(username,name,password,telephone,email,sex,Integer.valueOf(usertype)
                    ,department,age,level,advantage,address,Integer.valueOf(userid));
            result.put("success", true);
            return result;
        } else {
            result.put("msg", "账号已被使用");
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

    @RequestMapping("/getAdmin")
    public Map<String, Object> getAdmin() throws Exception {
        Map<String, Object> result = userService.getAdmin();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getLevel")
    public List<Map<String, Object>> getLevel() throws Exception {
        List<String> levels = userService.getLevel();
        List<Map<String, Object>> results = new ArrayList<>();
        for (String level : levels) {
            Map<String, Object> result = new HashMap<>();
            result.put("text", level);
            result.put("value", level);
            results.add(result);
        }
        return results;
    }

    @RequestMapping("/getDepartment")
    public List<Map<String, Object>> getDepartment() throws Exception {
        List<String> departments = userService.getDepartment();
        List<Map<String, Object>> results = new ArrayList<>();
        for (String department : departments) {
            Map<String, Object> result = new HashMap<>();
            result.put("text", department);
            result.put("value", department);
            results.add(result);
        }
        return results;
    }

    @RequestMapping("/getTJDepartment")
    public List<Map<String, Object>> getTJDepartment() throws Exception {
        List<String> departments = userService.getDepartment();
        List<Map<String, Object>> results = new ArrayList<>();
        HashMap<String, Object> qb = new HashMap<>();
        qb.put("label", "全部");
        qb.put("value", "全部");
        results.add(qb);
        for (String department : departments) {
            Map<String, Object> result = new HashMap<>();
            result.put("label", department);
            result.put("value", department);
            results.add(result);
        }
        return results;
    }

    @RequestMapping("/getTJDoctor")
    public List<Map<String, Object>> getTJDoctor(@RequestBody Map<String, String> params) throws Exception {
        String department = params.get("department");
        String sub = "";
        if (!department.isEmpty() && !department.equals("全部")) {
            sub += " and department='"+department+"'";
        }
        List<Map<String, Object>> doctors = userService.getTJDoctor(sub);
        List<Map<String, Object>> results = new ArrayList<>();
        HashMap<String, Object> qb = new HashMap<>();
        qb.put("label", "全部");
        qb.put("value", "全部");
        results.add(qb);
        for (Map<String, Object> doctor : doctors) {
            Map<String, Object> result = new HashMap<>();
            result.put("value", doctor.get("userid"));
            result.put("label", doctor.get("name"));
            results.add(result);
        }
        return results;
    }

    @RequestMapping("/getStatistics")
    public List<Map<String, Object>> getStatistics(@RequestBody Map<String, String> params) throws Exception {
        String tjdate = params.get("tjdate");
        String tjdepartment = params.get("tjdepartment");
        String tjdoctor = params.get("tjdoctor");
        String sub = "";
        if (!tjdate.isEmpty() && !tjdate.equals("全部")) {
            sub += " and str_to_date(af2.date,'%Y-%c-%d') = str_to_date(now(),'%Y-%c-%d')";
        }
        if (!tjdepartment.isEmpty() && !tjdepartment.equals("全部")) {
            sub += " and user.department='"+tjdepartment+"'";
        }
        if (!tjdoctor.isEmpty() && !tjdoctor.equals("全部")) {
            sub += " and user.userid='"+tjdoctor+"'";
        }
        return userService.getStatistics(sub);
    }

    @RequestMapping("/getStatisticsPie")
    public List<Map<String, Object>> getStatisticsPie(@RequestBody Map<String, String> params) throws Exception {
        String tjdate = params.get("tjdate");
        String tjdepartment = params.get("tjdepartment");
        String tjdoctor = params.get("tjdoctor");
        String sub = "";
        if (!tjdate.isEmpty() && !tjdate.equals("全部")) {
            sub += " and str_to_date(af2.date,'%Y-%c-%d') = str_to_date(now(),'%Y-%c-%d')";
        }
        if (!tjdepartment.isEmpty() && !tjdepartment.equals("全部")) {
            sub += " and user.department='"+tjdepartment+"'";
        }
        if (!tjdoctor.isEmpty() && !tjdoctor.equals("全部")) {
            sub += " and user.userid='"+tjdoctor+"'";
        }
        List<Map<String, Object>> statisticsPie = userService.getStatisticsPie(sub);
        if (statisticsPie.isEmpty()) {
            Map<String, Object> result = new HashMap<>();
            result.put("value", 0);
            result.put("name", "无");
            statisticsPie.add(result);
        }
        return statisticsPie;
    }
}
