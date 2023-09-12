package com.langjialing.helloworld.controller;

import com.jcraft.jsch.SftpException;
import com.langjialing.helloworld.config.util.SFTP;
import com.langjialing.helloworld.config.util.SftpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 郎家岭伯爵
 * @time 2023/9/12 8:58
 */

@RestController
@RequestMapping("/sftp")
@Slf4j
public class SFTPController {

    @GetMapping("/t")
    public void sftp() {
        SFTP ftp = new SFTP(3, 6000);
        SftpConfig sftpConfig = new SftpConfig("192.168.91.128", 2222,
                "langjialing", "123456", 1000, "/upload/excel_20230911.xlsx");
        try {
            List<String> list = ftp.listFiles("/files/202309", sftpConfig);
            log.info("文件上传下载详情{}"  , new Object[]{list});
        } catch (SftpException e) {
            log.error("文件上传下载异常:{}" , e.getMessage());
        }
    }

    @GetMapping("/t1")
    public void test1(){
        SFTP ftp = new SFTP(3, 6000);
        SftpConfig sftpConfig = new SftpConfig("192.168.91.128", 2222,
                "langjialing", "123456", 1000, "/upload");

        ftp.download("/upload", "excel_20230911.xlsx", "./files/202309", sftpConfig);
    }
}
