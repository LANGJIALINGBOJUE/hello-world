package com.langjialing.helloworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 郎家岭伯爵
 */
@RestController
@Slf4j
public class FileController {
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        try {

            log.info("文件名称为：{}",file.getOriginalFilename());
            log.info("文件扩展名为：{}",file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));

            // 针对文件重名问题，这里可以用UUID或者时间戳来重命名文件，例如：
            String uid = UUID.randomUUID().toString();
            String newName = uid + "_" +file.getOriginalFilename();
            log.info("重命名文件名称为：{}", newName);

            // 文件大小校验
            if (file.getSize()>10*1024*1024){
                return "文件过大，不允许上传";
            }

            // 本地文件保存位置
            String uploadPath = "D://Java/HelloWorld/src/main/resources/files";
            File uploadDir = new File(uploadPath);
            // 位置不存在则创建位置
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            log.info(uploadDir.getAbsolutePath());

            // 本地文件
            File localFile = new File(uploadPath + File.separator + file.getOriginalFilename());
//            如果文件需要重命名，可替换为如下方式
//            File localFile = new File(uploadPath + File.separator + newName);

            // transfer to local
            file.transferTo(localFile);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAILED";
        }
        return "SUCCESS";
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        response.reset();
        response.setContentType("application/octet-stream");
        // filename为下载后的文件名。通常在业务中我们会在数据库中保存文件的路径及名称，因此下载后的文件名称可以按实际需要进行命名。这里我们使用时间戳来命名文件
        response.setHeader("Content-disposition",
                "attachment;filename=file_" + System.currentTimeMillis() + ".txt");

        // 从文件读到servlet response输出流中
        // 这里需要指明具体的文件路径及文件名
        File file = new File("D:/Java/HelloWorld/src/main/resources/files/账号密码.txt");
        try (FileInputStream inputStream = new FileInputStream(file);) {
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
