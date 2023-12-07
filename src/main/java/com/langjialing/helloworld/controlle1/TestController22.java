package com.langjialing.helloworld.controlle1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.langjialing.helloworld.mapper.UserMapper;
import com.langjialing.helloworld.model.entity.UserEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 郎家岭伯爵
 * @time 2023/10/20 15:50
 */
@RestController
@RequestMapping("/test22")
public class TestController22 {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/t1")
    public String test1(){
        return "test1";
    }

    @GetMapping("/t2")
    public void test2(){
        String s = "{\"userName\": \"郎家岭伯爵\", \"password\": \"123456\"}";
        UserEntity userEntity = JSON.parseObject(s, UserEntity.class);
        System.out.println(userEntity.getUserName());
        System.out.println(userEntity.getPassword());
        System.out.println(userEntity.getId());

        System.out.println(userEntity.toString());
    }

    @GetMapping("/t3")
    public void test3(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", "langjialing");
        jsonObject.put("password", "123456");

        System.out.println(JSON.toJSONString(jsonObject));
        System.out.println(jsonObject.get("userName"));

    }

    @GetMapping("/t4")
    public void test4(){
        List<UserEntity> list = userMapper.getUserList(new UserEntity().setAge(222L));
        System.out.println(list);
        System.out.println(list.size());
    }

    @GetMapping("/t5")
    public void test5(){
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map1 = new HashMap<>(10);
        map1.put("langjialing", "1");
        Map<String, String> map2 = new HashMap<>(10);
        map2.put("langjialing", "2");
        Map<String, String> map3 = new HashMap<>(10);
        map3.put("langjialing", "3");

        list.add(map1);
        list.add(map2);
        list.add(map3);

        List<String> langjialing = list.stream().map(map -> map.get("langjialing")).collect(Collectors.toList());
        System.out.println(langjialing);

        List<String> list1 = new ArrayList<>();
        list1.addAll(list.stream().map(map -> map.get("langjialing")).collect(Collectors.toList()));
        System.out.println(list1);
    }

    @GetMapping("/t6")
    public void test6(){
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map.put("langjialing", "123");
        map2.put("123",map);

        Map<String, String> map1 = (Map<String, String>) map2.get("123");
        System.out.println(map1);
    }

    @GetMapping("/t7")
    public void test7(){
        String s = null;
        String s1 = "";

        System.out.println(StringUtils.isEmpty(s));
        System.out.println(StringUtils.isEmpty(s1));
    }

    @GetMapping("/t8")
    public void test8(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lang", "");
        System.out.println("获取的String为：" + jsonObject.getString("lang"));
        System.out.println("对获取的String对象进行操作：" + jsonObject.getString("lang"));
        System.out.println("获取的JSONObject为：" + jsonObject.getJSONObject("lang"));
        System.out.println("对获取的JSONObject对象进行操作：" + jsonObject.getJSONObject("lang").toJSONString());
    }

    @GetMapping("/t9")
    public void test9(){
        int i = 1;

        switch (i){
            case 1:
                System.out.println("case 1:" + i);
            case 2:
                System.out.println("case 2:" + i);
                i+=2;
            case 3:
                System.out.println("case 3:" + i);
            default:
                System.out.println("default");
            case 4:
                System.out.println("case 4:" + i);
                break;
//                return;
            case 5:
                System.out.println("case 5:" + i);
        }
        System.out.println("i 为:" + i);
    }

    @GetMapping("/t10")
    public void test10(){
        int i = 1;

        switch (i){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("case 5:" + i);
        }
        System.out.println("i 为:" + i);
    }

    @GetMapping("/t11")
    public void test11(){

        // 创建数据
        List<Map<String, Object>> list = new ArrayList<>(List.of(
                Map.of("key", "10"),
                Map.of("key", "5"),
                Map.of("key", "25"),
                Map.of("key", "30"),
                Map.of("key", 50)));

        /*
            或者可以这样创建数据。
            List<Map<String, Object>> list = new ArrayList<>();
            list.add(Map.of("yourKey", 10));
            list.add(Map.of("yourKey", 5));
            list.add(Map.of("yourKey", 25));
            list.add(Map.of("yourKey", 30));
            list.add(Map.of("yourKey", 50));
         */


        /*
            这里注意避坑。这是一个不可变List，无法排序。
            List<Map<String, Object>> list = List.of(
                Map.of("key", 10),
                Map.of("key", 5),
                Map.of("key", 25),
                Map.of("key", 30),
                Map.of("key", 50));
         */

        System.out.println("排序前：" + list);

        list = list.stream().sorted(Comparator.comparing(m -> String.valueOf(m.get("key")))).collect(Collectors.toList());

        System.out.println("排序后：" + list);


        // 指定分页参数
        int pageSize = 2; // 每页的大小
        int page = 2;     // 要获取的页数（从1开始）

        // 计算起始索引和结束索引
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());

        // 获取分页结果
        list = list.subList(startIndex, endIndex);

        // 打印分页结果
        System.out.println("分页List：" + list);
    }

    @GetMapping("/t12")
    public void test12(){
        // 创建数据
        List<Map<String, Object>> list = new ArrayList<>(List.of(
                Map.of("key", 10),
                Map.of("key", 5),
                Map.of("key", 25),
                Map.of("key", 30),
                Map.of("key", 50)));

        System.out.println("排序前：" + list);

        List<Map<String, Object>> list1 = list.stream()
                .sorted(Comparator.comparing(m -> (int) m.get("key"))).collect(Collectors.toList());

        System.out.println("排序后：" + list1);

        // 指定分页参数
        int pageSize = 2; // 每页的大小
        int page = 2;     // 要获取的页数（从1开始）

        // 计算起始索引和结束索引
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(pageSize, list.size() - (page - 1) * pageSize);

        List<Map<String, Object>> key = list.stream()
                .sorted(Comparator.comparing(m -> (int) m.get("key")))
                .skip(startIndex)
                .limit(endIndex)
                .collect(Collectors.toList());

        System.out.println("排序加分页后：" + key);

    }

    @GetMapping("/t13")
    public void test13(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        System.out.println(sdf.format(calendar.getTime()));
    }

    @GetMapping(value = "/t14", produces = {"text/html;charset=utf-8"})
    public String test14(@RequestParam String s){
        List<String> list = new ArrayList<>(List.of("(1)", "（2）"));
        if (list.contains(s)){
            return s;
        } else {
            return "不包含";
        }
    }
}
