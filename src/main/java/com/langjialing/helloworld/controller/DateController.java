package com.langjialing.helloworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 郎家岭伯爵
 * @time 2023/7/6 13:59
 */
@RestController
@RequestMapping("/date")
@Slf4j
public class DateController {
    @GetMapping("/date")
    public void getDate() throws Exception{
        String beginOpTime = "2021-07-06 13:50:00";
        String endOpTime = "2023-09-16 15:55:01";

        // 计算endOptime和beginOpTime的时间差
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(endOpTime);
        Date date2 = sdf.parse(beginOpTime);
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff = time1 - time2;

        // 计算出相差年数、月数、天数和小时数
        long year = diff / (365L * 24 * 60 * 60 * 1000);
        long month = diff / (30L * 24 * 60 * 60 * 1000) - year * 12;
        long day = diff / (24 * 60 * 60 * 1000) - year * 365 - month * 30;
        long hour = diff / (60 * 60 * 1000) - year * 365 * 24 - month * 30 * 24 - day * 24;
        long minute = diff / (60 * 1000) - year * 365 * 24 * 60 - month * 30 * 24 * 60 - day * 24 * 60 - hour * 60;
        long second = diff / 1000 - year * 365 * 24 * 60 * 60 - month * 30 * 24 * 60 * 60 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60;
        String s = "耗时" + year + "年" + month + "月" + day + "天" + hour + "小时" + minute + "分钟" + second + "秒";
        System.out.println(s);
    }
}
