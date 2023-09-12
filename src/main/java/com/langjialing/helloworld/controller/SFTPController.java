package com.langjialing.helloworld.controller;

import com.jcraft.jsch.SftpException;
import com.langjialing.helloworld.config.util.SFTP;
import com.langjialing.helloworld.config.util.SftpConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author 郎家岭伯爵
 * @time 2023/9/12 8:58
 */

@RestController
@RequestMapping("/sftp")
@Slf4j
public class SFTPController {

    @Value("${sftp.host}")
    private String host;
    @Value("${sftp.port}")
    private int port;
    @Value("${sftp.username}")
    private String username;
    @Value("${sftp.password}")
    private String password;
    @Value("${sftp.timeout}")
    private int timeout;
    @Value("${sftp.remoteRootPath}")
    private String remoteRootPath;

    @Value("${sftp.directory}")
    private String directory;
    @Value("${sftp.saveFile}")
    private String saveFile;

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
        SftpConfig sftpConfig = new SftpConfig(host, port, username, password, timeout, remoteRootPath);

        ftp.download(directory, "excel_20230911.xlsx", saveFile + "202309", sftpConfig);
        handExcelData(saveFile + "202309/" + "excel_20230911.xlsx");
    }

    public void handExcelData(String excelFilePath){

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue());
                            break;
                        default:
                            System.out.print("");
                    }
                    // 列之间用制表符分隔
                    System.out.print("\t");
                }
                // 换行处理下一行
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
