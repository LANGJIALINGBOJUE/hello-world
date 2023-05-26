package com.langjialing.helloworld.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.langjialing.helloworld.mapper.UserMapper;
import com.langjialing.helloworld.model.entity.UserEntity;
import com.langjialing.helloworld.service.IFirstService;
import com.langjialing.helloworld.service.ISecondService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/14 14:45
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IFirstService firstService;

    @Autowired
    private ISecondService secondService;

    @PostMapping("/t1")
    public void test(@RequestBody UserEntity user){
        UserEntity user1 = new UserEntity().setUserName("langjialing");

        log.info(user1.getUserName());
        user1.setUserName(user.getUserName());
        log.info(user1.getUserName());
    }

    @PostMapping("t2")
    public void test2(@RequestBody UserEntity user){
//        userMapper.insertUser(user);
    }

    @GetMapping("/t3")
    public String test3(@RequestParam String s){
        return s;
    }

    @PostMapping("/t4")
    public void addUsers(@RequestBody @Validated List<UserEntity> users){
        BigDecimal bigDecimal = new BigDecimal("100");
        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            log.info("0");
        }
        if (bigDecimal.compareTo(BigDecimal.valueOf(100))==0){
            log.info("100");
        }

        String s = "1,2,3";
        // 把s转为Integer类型的数组
        String[] split = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s1 : split) {
            list.add(Integer.valueOf(s1));
        }
        log.info(list.toString());

        String s1 = "[1,2, 3]";
    }

    @GetMapping("/t5")
    public void test5(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String format = sdf.format(new Date());
        // 把format转为Integer
        Integer year = Integer.valueOf(format);
        log.info(format);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
        // 获取当前时间的上一月
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        String format1 = sdf1.format(calendar.getTime());
        Integer month = Integer.valueOf(format1);
        log.info(format1);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        // 获取上月最后一天
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, 0);
        String format2 = sdf2.format(calendar1.getTime());
        log.info(format2);
        // 获取去年最后一天
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_YEAR, 0);
        String format3 = sdf2.format(calendar2.getTime());
        log.info(format3);
    }

    @GetMapping("/t6")
    public void test6(){
        BigDecimal num1 = new BigDecimal("100");
        BigDecimal num2 = new BigDecimal("20");
        // 计算num1除以num2的结果，保留两位小数，四舍五入
        BigDecimal divide = num1.divide(num2, 2, RoundingMode.HALF_UP);
        // 计算num1减去num2的结果
        BigDecimal subtract = num1.subtract(num2);
        log.info(divide.toString());
        log.info(subtract.toString());
    }

    @GetMapping("/t7")
    public void test7(){
        for (int i= 0; i < 10; i++) {
            if (i == 5){
                continue;
            }
            log.info("i = {}", i);
        }
    }

    @GetMapping("/t8")
    public void test8(){
        BigDecimal num1 = new BigDecimal("0");
        // 计算num1从0加到10的值
        for (int i = 0; i < 10; i++) {
            num1 = num1.add(BigDecimal.valueOf(i));
        }
    }

    @GetMapping("/t9")
    public void test9(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        // 遍历map
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            log.info("key = {}, value = {}", entry.getKey(), entry.getValue());
        }
    }

    @GetMapping("/t10")
    public void test10(){
        UserEntity userEntity = new UserEntity().setAge(20L).setPassword("123").setUserName("langjialing");
        UserEntity userEntity1 = new UserEntity().setAge(20L).setPassword("456").setUserName("langjialing456");
        UserEntity userEntity2 = new UserEntity().setAge(25L).setPassword("789").setUserName("langjialing789");
        UserEntity userEntity3 = new UserEntity().setAge(25L).setPassword("123").setUserName("langjialing");

        List<UserEntity> list = new ArrayList<>();
        list.add(userEntity);
        list.add(userEntity1);
        list.add(userEntity2);
        list.add(userEntity3);

        // 把list按年龄分组
        Map<Long, List<UserEntity>> collect = list.stream().collect(Collectors.groupingBy(UserEntity::getAge));
        // 遍历map
        for (Map.Entry<Long, List<UserEntity>> entry : collect.entrySet()) {
            log.info("key = {}, value = {}", entry.getKey(), entry.getValue());
        }
        log.info(collect.toString());

        // 把list按年龄分组，然后把每个分组的用户名拼接起来
        Map<Long, String> collect1 = list.stream().collect(Collectors.groupingBy(UserEntity::getAge, Collectors.mapping(UserEntity::getUserName, Collectors.joining(","))));
        log.info(collect1.toString());
    }

    @GetMapping("/t11")
    public void test11(){
        // 定义一个HashMap，键为String，值为任意数据类型
        HashMap<String, Object> map = new HashMap<>();

        HashMap<String, String> hash = new HashMap<>();
        hash.put("a", "1");

        map.put("a", hash);

        BigDecimal i = new BigDecimal("1");
        // 把i转为万元
        BigDecimal divide = i.divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP);
        System.out.println(divide);

    }

    @GetMapping("/t12")
    public void test12(){
        log.info("姓名：{}，年龄：{}", "langjialing", 20);

        String s = "32.2355";
        // s四舍五入，保留2位非零小数
        DecimalFormat df = new DecimalFormat("#.##");
        String format = df.format(Double.valueOf(s));
        log.info(format);
    }

    @GetMapping("/t13")
    public String test13(@RequestParam String value){
        // # 表示数字占位符，.## 表示保留至少一位小数，最多两位小数
        DecimalFormat df = new DecimalFormat("#.##");
        String format = df.format(Double.valueOf(value));
        log.info(format);
        return format;
    }

    @GetMapping("/t14")
    public void test14(){
        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b); // 输出 true

        Integer c = 200;
        Integer d = 200;
        System.out.println(c == d); // 输出 false

    }

    @GetMapping("/t15")
    public void test15(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(i + 1);
        }

        List<Map> list1 = new ArrayList<>();
        for (int i=0; i < list.size(); i++) {
            Map<String, String> map = new HashMap();
            map.put("num", list.get(i).toString());
            list1.add(map);
            if (list1.size() == 3 || i == list.size() - 1){
                userMapper.updateUsersInfo(list1, 25);
                list1.clear();
            }
        }

    }

    @GetMapping("t16")
    public void test16() throws InterruptedException {
        Boolean flag = null;
        if (flag==null) {
            System.out.println("true");
        }

        UserEntity user = new UserEntity();
        if (user.getUserName()=="1"){
            System.out.println("user1");
        }
        if (user.getUserName() == null){
            System.out.println("null");
        }

        if (flag == null) {
            System.out.println("false");
        }

        Thread.sleep(10000);
    }

    @GetMapping("t17")
    public void test17() throws InterruptedException {
        test16();
        System.out.println("t17");
    }

    @GetMapping("t18")
    public void test18(){
        firstService.callSecondService();
        secondService.callFirstService();
    }

    @GetMapping("t19")
    public void test19(){
        List list = new ArrayList();
        list.add(1);
        list.add(0);

        // 判断list中的元素是否都为0或1
        boolean allMatch = list.stream().allMatch(e -> e.equals(0) || e.equals(1));
        System.out.println(allMatch);
    }

    @GetMapping("t20")
    public void test20(){
        UserEntity user1 = new UserEntity();
        user1.setUserName("123");

        UserEntity user2 = new UserEntity();
        user2.setUserName(user1.getUserName());
        user2.setPassword(user1.getPassword());

        if (user2.getUserName() != null){
            System.out.println("user2Name不为空");
        }

        if (user2.getPassword() != null){
            System.out.println("user2Password不为空");
        }
    }

    @GetMapping("/t21")
    public void test21(){
        String s = "[1,2]";
        // 把s转为List
        List<Integer> list = JSON.parseArray(s, Integer.class);
    }

    @GetMapping("/t22")
    public void test22(){
        String s = "{\n" +
                "            \"regno\": \"\",\n" +
                "            \"xzqh\": \"福建省宁德市柘荣县\",\n" +
                "            \"zczj\": \"\",\n" +
                "            \"creditcode\": \"113522317438174256\",\n" +
                "            \"jgmc\": \"柘荣县应急管理局\",\n" +
                "            \"jgdz\": \"福建省宁德市柘荣县河洋西路1号\",\n" +
                "            \"bgrq\": \"\",\n" +
                "            \"url\": \"\",\n" +
                "            \"zcrq\": \"\",\n" +
                "            \"zfrq\": \"\",\n" +
                "            \"jyfw\": \"\",\n" +
                "            \"jjhydm\": \"\",\n" +
                "            \"jydz\": \"\",\n" +
                "            \"jgdm\": \"743817425\",\n" +
                "            \"jglx\": \"机关\",\n" +
                "            \"bzrq\": \"\",\n" +
                "            \"fddbr\": \"温科强\",\n" +
                "            \"bzjg\": \"柘荣县事业单位登记管理局\",\n" +
                "            \"jyzt\": \"正常\",\n" +
                "            \"jjlxdm\": \"\"\n" +
                "        }" +
                "\n" +
                "\n";
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println("1"+jsonObject.get("bzjg"));
        System.out.println("2"+jsonObject.get("bzjg1"));

    }

    @GetMapping("/t23")
    public String test23() throws InterruptedException {
        // 等待半秒
        Thread.sleep(500);
        return "t23";
    }

}
