package com.linyuang.www;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@SpringBootTest
@Slf4j
class AnyviewSecondApplicationTests {
    public static void main(String[] args) throws Exception{
        String zipPath = "knowledge_point_list.zip";
        String targetName = "Attr.xlsx";
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            ZipFile zipFile = new ZipFile(zipPath);
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

    @Test
    void contextLoads() {
    }

    @RequestMapping
    public String fileUpload(HttpServletRequest request) throws Exception {
        //设置上传位置
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        File file = new File(path);
        //判断上传位置是否存在，不存在则创建之
        if(!file.exists()){
            file.mkdirs();
        }
        //从request获取上传的文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload =  new ServletFileUpload(factory);
        //获得所有文件项
        List<FileItem> fileItems = upload.parseRequest(request);
        //遍历每一个文件项
        for (FileItem fileItem : fileItems) {//判断当前文件项是不是上传文件项
            if (fileItem.isFormField()) {
                //普通表单项
            } else {
                //上传文件项
                //获得上传文件的名称
                String fileItemName = fileItem.getName();
                //生成uuid保证上传的文件名的唯一性
                String uuid = UUID.randomUUID()
                        .toString()
                        .replace("-", "");
                fileItemName = uuid + "_" + fileItemName;
                //上传
                fileItem.write(new File(path, fileItemName));
                //删除临时文件
                fileItem.delete();
            }
        }
        return "success";
    }

    @Test
    public void test9() throws Exception{
        log.info("======");
        log.error("iuwsdhvioewhfvog");
    }
}
