package com.langjialing.helloworld.controller;

import com.jcraft.jsch.SftpException;
import com.langjialing.helloworld.config.util.SftpConfig;
import com.langjialing.helloworld.config.util.SftpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 郎家岭伯爵
 * @time 2023/9/12 8:58
 */

@RestController
@RequestMapping("/sftp")
@Slf4j
public class SftpController {

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
        SftpUtil ftp = new SftpUtil(3, 6000);
        SftpConfig sftpConfig = new SftpConfig("192.168.91.128", 2222,
                "langjialing", "123456", 1000, "/upload/excel_20230912.xlsx");
        try {
            List<String> list = ftp.listFiles("/upload", sftpConfig);
            log.info("文件上传下载详情{}"  , new Object[]{list});
        } catch (SftpException | UnsupportedEncodingException e) {
            log.error("文件上传下载异常:{}" , e.getMessage());
        }
    }

    @GetMapping("/upload")
    public void upload(@RequestParam String uploadFile){
        SftpUtil ftp = new SftpUtil(3, 6000);
        SftpConfig sftpConfig = new SftpConfig(host, port, username, password, timeout, remoteRootPath);

        ftp.upload(directory, uploadFile, sftpConfig);
    }

    @PostMapping("/uploadMultipleFiles")
    public void uploadMultipleFiles(@RequestBody List<String> uploadFiles){
        SftpUtil ftp = new SftpUtil(3, 6000);
        SftpConfig sftpConfig = new SftpConfig(host, port, username, password, timeout, remoteRootPath);

        ftp.uploadMultipleFiles(directory, uploadFiles, sftpConfig);
    }

    @GetMapping("/uploadDirectory")
    public void uploadDirectory(@RequestParam String uploadDirectory){
        SftpUtil ftp = new SftpUtil(3, 6000);
        SftpConfig sftpConfig = new SftpConfig(host, port, username, password, timeout, remoteRootPath);

        ftp.uploadDirectory(directory, uploadDirectory, sftpConfig);
    }

    @GetMapping("/download")
    public void download() throws SftpException, UnsupportedEncodingException {
        SftpUtil ftp = new SftpUtil(3, 6000);
        SftpConfig sftpConfig = new SftpConfig(host, port, username, password, timeout, remoteRootPath);

        // 列出远程目录中的文件列表
        List<String> remoteFileList = ftp.listFiles(directory, sftpConfig);
        System.out.println("文件列表为：" + remoteFileList);

        String targetFileName = "招标线索信息_20230912.xlsx";
        if (remoteFileList.contains(targetFileName)) {
            // 文件存在，可以进行下载操作
            System.out.println("文件存在，可以进行下载操作");
            ftp.download(directory, targetFileName, saveFile + "202309", sftpConfig);
            handExcelData(saveFile + "202309/" + targetFileName);
        } else {
            // 文件不存在，进行相应的处理
            System.out.println("文件不存在：" + targetFileName);
        }

        ftp.download(directory, targetFileName, saveFile + "202309", sftpConfig);
        handExcelData(saveFile + "202309/" + targetFileName);
    }

    public void handExcelData(String excelFilePath){

        // 按单元格数据类型处理数据
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

        // 把单元格内容全部识别为String类型，使用嵌套增强for循环进行遍历
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 格式化数据
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    System.out.print(cellValue);
                    // 列之间用制表符分隔
                    System.out.print("\t");
                }
                // 换行处理下一行
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 把单元格内容全部识别为String类型，使用嵌套普通for循环进行遍历，可读取指定位置的单元格
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 格式化数据
            DataFormatter dataFormatter = new DataFormatter();

            // 遍历行
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                // 遍历列
                for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
                    Cell cell = row.getCell(columnIndex);
                    String cellValue = dataFormatter.formatCellValue(cell);
                    System.out.print(cellValue);

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

    @GetMapping("/downloadDirFile")
    public void downloadDirFile(@RequestParam String path) throws Exception {
        SftpUtil ftp = new SftpUtil(3, 6000);
        SftpConfig sftpConfig = new SftpConfig(host, port, username, password, timeout, remoteRootPath);

        // 列出远程目录中的文件列表
        List<String> remoteFileList = ftp.listFiles(directory, sftpConfig);
        System.out.println("文件列表为：" + remoteFileList);

        ftp.downloadDirFile(path, saveFile + "202310", sftpConfig);
    }

    @GetMapping("/downloadDirectory")
    public void downloadDirectory(@RequestParam String path) throws Exception {
        SftpUtil ftp = new SftpUtil(3, 6000);
        SftpConfig sftpConfig = new SftpConfig(host, port, username, password, timeout, remoteRootPath);

        ftp.downloadDirectory(path, saveFile + "202310", sftpConfig);
    }
}
