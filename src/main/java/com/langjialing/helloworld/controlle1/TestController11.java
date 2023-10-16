package com.langjialing.helloworld.controlle1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.langjialing.helloworld.model.MyAutoClosable;
import com.langjialing.helloworld.model.entity.UserEntity;
import com.mysql.cj.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 郎家岭伯爵
 * @Description test
 * @Date 2023/9/10 22:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/test11")
public class TestController11 {
    @GetMapping("t")
    public void test(){
        Map<String, String> map = new HashMap<>(10);
        map.put("key", "");
        map.put("key1", null);
        System.out.println(map.get("key"));
        System.out.println(map.get("key1"));
    }

    @GetMapping("/t1")
    public void test1(){
        int i = 1;

        for (; i<10;){
            System.out.println("当前i为：" + String.valueOf(i+=1));
        }
    }

    @GetMapping("t2")
    public void test2(@RequestParam String s){
        // 去掉方括号
        s = s.trim().substring(1, s.length() - 1);

        if (!s.contains(",")){
            return;
        }

        // 以逗号分隔字符串并去除空格
        String[] parts = s.split(",");

        List<Integer> list = new ArrayList<>();

        // 遍历字符串数组，将每个字符串解析为整数并添加到List中
        for (String part : parts) {
            int num = Integer.parseInt(part.trim());
            list.add(num);
        }

        System.out.println(list);
    }

    @GetMapping("/t3")
    public void test3(){
        JSONObject jsonObject = new JSONObject();

        System.out.println(jsonObject.getInteger("id"));
    }

    @GetMapping("/t4")
    public void test4(){
        Map<String, String> map = new HashMap<>(10);
        map.put("key", "value");
        System.out.println(JSON.toJSONString(map));
    }

    @GetMapping("/t5")
    public void test5(){

        Map<String, String> map = new HashMap<>(10);
        map.put("id", "");

        // lywtodo
        System.out.println((Integer) 5);
    }

    @GetMapping("/t6")
    public void test6() throws FileNotFoundException {
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader("d:/input.txt");
            br = new BufferedReader(fr);
            String s = "";
            while((s = br.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/t7")
    public void test7() throws FileNotFoundException {
        try(
            FileReader fr = new FileReader("d:/input.txt");
            BufferedReader br = new BufferedReader(fr)
        ){
            String s = "";
            while((s = br.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("t8")
    public void test8(){
        try(MyAutoClosable myAutoClosable = new MyAutoClosable()){
            myAutoClosable.doIt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/t9")
    public void test9() {
        String s = "2023-03-28";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date date = sdf.parse(s);
            System.out.println("转换前日期：" + sdf.format(date));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // 延后五个月
            calendar.add(Calendar.MONTH, 5);

            System.out.println("转换后日期：" + sdf.format(calendar.getTime()));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/t10")
    public void test10(){
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(10);
        userEntity.setUserName("langjialing");

        System.out.println(userEntity);
        System.out.println(userEntity.toString());
    }

    @GetMapping("/t11")
    public void test11(){
        label:
        for (int i = 0; i < 3; i++) {
            System.out.println("=========" + i + "==============");
            for (int j = 0; j < 3; j++) {
                if (i == 1){
                    continue label;
                }
                System.out.println("++++++++++" + j + "+++++++++");
            }
        }
    }
}
