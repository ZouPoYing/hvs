package com.graduation.hvs.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    /**
     * 上传文件
     *
     * @param multipartFile
     * @return 文件存储路径
     */
    public static String upload(MultipartFile multipartFile,String userid) throws Exception {
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储
        String filename =  userid + "_" + System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        String filePath = "E:/IDEAProjects/hvs/src/main/resources/static/" + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}