package com.graduation.hvs.utils;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDownLoad {

    /**
     * 下载文件
     *
     * @param request  /
     * @param response /
     * @param file     /
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file, boolean deleteOnExit) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            //response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                    if (deleteOnExit) {
                        //file.deleteOnExit();
                        file.delete();
                        System.out.println("已删除");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
