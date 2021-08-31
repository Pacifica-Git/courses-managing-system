package com.linyuang.www.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    /**
     *获取项目根路径下文件的路径
     * @param fileName 指定项目根路径下文件的文件名
     * @return java.lang.String
     */
    public static String getProjectFilePath(String fileName){
        String classesPath = FileUtil.class.getResource("/").getPath();
        String tempPath = classesPath.substring(0,classesPath.lastIndexOf("/"));
        tempPath = tempPath.substring(0,tempPath.lastIndexOf("/"));
        return tempPath.substring(0,tempPath.lastIndexOf("/") + 1) + fileName;
    }

    /**
     *将前端传递的文件上传至项目目录并返回文件名
     * @param multipartFile 接收前端的文件
     * @return java.lang.String
     */
    public static String upload(MultipartFile multipartFile) throws IOException {
        //设置上传路径为项目目录
        String path = "/";
        if(multipartFile == null || multipartFile.getOriginalFilename() == null){
            throw new IllegalArgumentException("未接收到文件或无法获取文件名");
        }
        //以上传的文件名字作为zip文件名字即可
        String originalFilename = multipartFile.getOriginalFilename();
        //上传
        multipartFile.transferTo(new File(path,originalFilename));
        return originalFilename;
    }
}
