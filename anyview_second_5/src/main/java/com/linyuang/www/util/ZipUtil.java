package com.linyuang.www.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/**
 * zip工具类
 * @author Lenovo
 */
public class ZipUtil {
    /**
     *解压项目目录下zip文件并获得指定的文件
     * @param zipFileName zip文件的名字
     * @param targetName 目标文件名
     */
    public static void unZip(String zipFileName, String targetName) throws IOException {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            ZipFile zipFile = new ZipFile(zipFileName);
            //使用ZipFile中的stream()方法并对zip内文件进行过滤
            Stream<? extends ZipEntry> stream = zipFile.stream()
                    .filter(zipEntry -> targetName.equals(zipEntry.getName()));
            Object[] objects = stream.toArray();
            ZipEntry zipEntry;
            if(objects.length == 1){
                zipEntry = (ZipEntry) objects[0];
                //ZipFile类的getInputStream()方法可以打开一个文件条目的输入流
                inputStream = zipFile.getInputStream(zipEntry);
                fileOutputStream = new FileOutputStream(zipEntry.getName());
                int count;
                //一直读取到文件结尾
                while ((count = inputStream.read()) != -1){
                    fileOutputStream.write(count);
                }
            }else{
                throw new RuntimeException("找不到要求的文件或文件数大于一");
            }
        }finally {
            if(fileOutputStream != null){
                fileOutputStream.close();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
    }
}
