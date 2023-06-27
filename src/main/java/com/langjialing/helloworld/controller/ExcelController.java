package com.langjialing.helloworld.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郎家岭伯爵
 * @time 2023/5/16 15:18
 */
@RestController
public class ExcelController {
    @GetMapping("/excel")
    public void excel(){
        // 读取指定路径的excel文件
        String inputFile = "D://证件号.xlsx";
        // 写入指定路径的excel文件，如果文件不存在则创建文件
        String outputFile = "D://证件号1.xlsx";

        // 读取Excel文件
        ExcelReader reader = ExcelUtil.getReader(inputFile);
        List<List<Object>> data = reader.read();

        // 处理Excel数据
        List<String> result = new ArrayList<String>();
        for (List<Object> row : data) {
            for (Object cell : row) {
                cell = cell + "111";
                result.add(cell.toString());
            }
        }

        // 将处理后的数据写入Excel文件
        ExcelWriter writer = ExcelUtil.getWriter(outputFile);
        writer.write(result);
        writer.close();

        System.out.println("Data processed successfully and written to output file.");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() throws IOException {
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // 创建数据
        createData(sheet);

        // 将工作簿写入字节数组输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=data.xlsx");

        // 返回Excel文件的字节数组
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/exportByStream")
    public void exportDataByStream(HttpServletResponse response) throws IOException {
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // 创建数据
        createData(sheet);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");

        OutputStream outputStream1 = response.getOutputStream();
        workbook.write(outputStream1);
        workbook.close();

        outputStream1.flush();
        outputStream1.close();
    }

    private void createData(Sheet sheet) {

        // 创建标题行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("省份");


        CellRangeAddress headerRange1 = new CellRangeAddress(0, 1, 0, 1);
        sheet.addMergedRegion(headerRange1);

        headerRow.createCell(2).setCellValue("各级领导查看人数");
        CellRangeAddress headerRange2 = new CellRangeAddress(0, 0, 2, 4);
        sheet.addMergedRegion(headerRange2);

        headerRow.createCell(5).setCellValue("所选月预警推送人数");
        CellRangeAddress headerRange3 = new CellRangeAddress(0, 0, 5, 7);
        sheet.addMergedRegion(headerRange3);

        headerRow.createCell(8).setCellValue("查看占比");
        CellRangeAddress headerRange4 = new CellRangeAddress(0, 0, 8, 10);
        sheet.addMergedRegion(headerRange4);

        Row headerRow1 = sheet.createRow(1);
        headerRow1.createCell(2).setCellValue("省级");
        headerRow1.createCell(3).setCellValue("地市级");
        headerRow1.createCell(4).setCellValue("总计");
        headerRow1.createCell(5).setCellValue("省级");
        headerRow1.createCell(6).setCellValue("地市级");
        headerRow1.createCell(7).setCellValue("总计");
        headerRow1.createCell(8).setCellValue("省级");
        headerRow1.createCell(9).setCellValue("地市级");
        headerRow1.createCell(10).setCellValue("总计");

//        // 创建数据行
//        Row dataRow = sheet.createRow(1);
//        dataRow.createCell(0).setCellValue("张三");
//        dataRow.createCell(1).setCellValue(25);
//
//        dataRow = sheet.createRow(2);
//        dataRow.createCell(0).setCellValue("李四");
//        dataRow.createCell(1).setCellValue(30);
    }

}
