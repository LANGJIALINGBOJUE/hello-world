package com.langjialing.helloworld;

import com.jcraft.jsch.SftpException;
import com.langjialing.helloworld.config.util.SFTP;
import com.langjialing.helloworld.config.util.SftpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName: TestSFTPUtils
 * @Description: SFTP工具类测试类
 * @Author: 尚先生
 * @CreateDate: 2019/4/25 11:09
 * @Version: 1.0
 */
@SpringBootTest
public class TestSFTPUtils {

    private static final Logger logger = LoggerFactory.getLogger(TestSFTPUtils.class);

    public static void main(String[] args) {
        SFTP ftp = new SFTP(3, 6000);
        SftpConfig sftpConfig = new SftpConfig("192.168.91.128", 2222,
                "langjialing", "123456", 1000, "/upload/excel_20230911.xlsx");
        try {
            List<String> list = ftp.listFiles("/files/202309", sftpConfig);
            logger.info("文件上传下载详情{}"  , list);
        } catch (SftpException e) {
            logger.error("文件上传下载异常:{}" , e.getMessage());
        }
    }
}
