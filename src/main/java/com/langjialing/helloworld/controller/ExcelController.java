package com.langjialing.helloworld.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
