package com.graduation.hvs.controller;

import com.graduation.hvs.dao.Set;
import com.graduation.hvs.dao.User;
import com.graduation.hvs.service.FileService;
import com.graduation.hvs.service.SetService;
import com.graduation.hvs.service.UserService;
import com.graduation.hvs.utils.FileDownLoad;
import com.graduation.hvs.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/set")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class SetController {

    @Autowired(required = false)
    private SetService setService;

    @Autowired(required = false)
    private FileService fileService;

    @Autowired(required = false)
    private UserService userService;

    @RequestMapping("/listSet")
    public List<Set> listSet() throws Exception {
        return setService.queryAllSet();
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userid")  String userid) throws Exception {
        if(userid == null){
            return "userid不能为空";
        }
        String filename = FileUploadUtil.upload(multipartFile,userid);
        fileService.fileUpload(filename,Integer.valueOf(userid));
        return fileService.getFileByFilename(filename).getFileid().toString();
    }

    @RequestMapping("/addSet")
    public Map<String, Object> addSet(@RequestBody Map<String, String> params) throws Exception {
        String filename = params.get("filename");
        String fileid = params.get("fileid");
        Map<String, Object> result = new HashMap<>();
        if (filename.isEmpty() || fileid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        setService.addSet(filename,Integer.valueOf(fileid));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getSetFile")
    public Map<String, Object> getSetFile(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        Map<String, Object> result = new HashMap<>();
        if (userid.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user = userService.getUserById(Integer.valueOf(userid));
        Set set = setService.getSetByUsertype(user.getUsertype());
        com.graduation.hvs.dao.File file = fileService.getFileByFileid(set.getFileid());
        result.put("success", true);
        result.put("filename", file.getFilename());
        result.put("name", set.getFilename());
        return result;
    }

    @GetMapping("/downloadFile/{filename}")
    public void downloadFile(@PathVariable("filename") String filename, HttpServletRequest request, HttpServletResponse response) {
        //1.获取文件绝对路径
        String path = "D:/IDEAProjects/hvs/src/main/resources/static/" + filename;
        //2.通过绝对路径定义File
        File f=new File(path);
        //3.调用FileUtil下载文件
        FileDownLoad.downloadFile(request,response,f,false);
    }

    @RequestMapping("/deleteSet")
    public Map<String, Object> deleteSet(@RequestBody Map<String, String> params) throws Exception {
        String id = params.get("id");
        Map<String, Object> result = new HashMap<>();
        if (id.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Set set = setService.selectById(Integer.valueOf(id));
        if (set.getPx()==1) {
            result.put("msg", "不能删除正在使用的病历本");
            return result;
        }
        setService.deleteById(Integer.valueOf(id));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateSet")
    public Map<String, Object> updateSet(@RequestBody Map<String, String> params) throws Exception {
        String id = params.get("id");
        String filename = params.get("filename");
        String px = params.get("px");
        Map<String, Object> result = new HashMap<>();
        if (id.isEmpty() || filename.isEmpty() || px.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (px.equals("是")) {
            px = "1";
            setService.updateOtherSet(Integer.valueOf(id));
        } else {
            px = "0";
        }
        setService.updateSet(filename,Integer.valueOf(px),Integer.valueOf(id));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getSetFilename")
    public Map<String, Object> getSetFilename() throws Exception {
        return setService.getSetFile();
    }
}
