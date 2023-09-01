package com.langjialing.helloworld.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.langjialing.helloworld.mapper.UserMapper;
import com.langjialing.helloworld.model.entity.UserEntity;
import com.langjialing.helloworld.service.IFirstService;
import com.langjialing.helloworld.service.ISecondService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 郎家岭伯爵
 * @time 2023/3/14 14:45
 */
@RestController("/test")
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
        log.info("========" + format2);

        // 获取上上月最后一天
        calendar1.add(Calendar.MONTH, -1);
        String format4 = sdf2.format(calendar1.getTime());
        log.info(format4);

        // 获取去年最后一天
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_YEAR, 0);
        String format3 = sdf2.format(calendar2.getTime());
        log.info(format3);

        // 获取指定年月的最后一天
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.YEAR, 2020);
        calendar3.set(Calendar.MONTH, 03);
        calendar3.set(Calendar.DAY_OF_MONTH, 0);
        String format5 = sdf2.format(calendar3.getTime());
        log.info(format5);

        // 获取当前年月日
        Calendar calendar4 = Calendar.getInstance();
        String format6 = sdf2.format(calendar4.getTime());
        log.info(format6);

        // 获取当前月
        Calendar calendar5 = Calendar.getInstance();
        calendar5.add(Calendar.MONTH, 0);
        String format7 = sdf1.format(calendar5.getTime());
        log.info(format7);

        // 获取当前时间
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
        String format8 = sdf3.format(new Date());
        log.info(format8);

        // 获取当前时间的前一年
        Calendar calendar6 = Calendar.getInstance();
        calendar6.add(Calendar.YEAR, -1);
        String format9 = sdf3.format(calendar6.getTime());
        log.info(format9);
    }

    @GetMapping("/t6")
    public void test6(){
        BigDecimal num1 = new BigDecimal("2");
        BigDecimal num2 = new BigDecimal("3");
        // 计算num1除以num2的结果，保留四位小数，四舍五入
        BigDecimal divide = num1.divide(num2, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
        log.info(divide + "%");
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

    @GetMapping("/t24")
    public void test24(){
        if (1==2){
            System.out.println("1=1");
        } else
        if(2==2){
            System.out.println("2=2");
        } else
        if (3==3){
            System.out.println("3=3");
        }
    }

    @GetMapping("/t25")
    public void test25(){
        UserEntity userEntity = new UserEntity();
        if (userEntity.getUserName()==null){
            System.out.println("null");
        }
    }

    @GetMapping("/t26")
    public void test26(){
        List<Integer> list = new ArrayList();

        for (Integer integer : list) {
            System.out.println("hello");
        }
    }

    @GetMapping("t27")
    public void test27(){
        String s = "{custMastCode:\"123\"}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject.get("custMastCode").toString());
    }

    @GetMapping("t28")
    public void test28(){
        String s = "202306";
        String s1 = s.substring(0,4);
        System.out.println("s1:" + s1);
        String s2 = s.substring(4,6);
        System.out.println(Integer.valueOf(s2));
        System.out.println("s2:" + s2);
    }

    @GetMapping("t29")
    public void test(){
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("hello", "world");
        System.out.println(map.toString());
        System.out.println(JSONObject.toJSONString(map));
        // 使用hutool类把map转为String
        String s = JSONUtil.toJsonStr(map);
        System.out.println(s);
    }

    @GetMapping("/t30")
    public void test30(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", "1");
        List<JSONObject> list = new ArrayList<>();
        list.add(jsonObject);
        jsonObject.clear();
        System.out.println(list.toString());
    }

    @GetMapping("/t31")
    public void test31() throws InterruptedException {
        String s = "12";
        if ("12".equals(s)){
            throw new RuntimeException("hello");
        }
    }

    @GetMapping("/t32")
    public void test32(){
        UserEntity userById = userMapper.getUserById(19);
        System.out.println(userById);
        System.out.println(userById.getUserName());
    }

    @GetMapping("/t33")
    public void test33(){
        BigDecimal bigDecimal = new BigDecimal("100");
        BigDecimal bigDecimal1 = new BigDecimal("5");
        System.out.println(bigDecimal.divide(bigDecimal1, 2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 这个输出结果是由于`JSONObject`类在处理循环引用时采用了特殊的处理方式。循环引用是指在 JSON 对象中存在相互引用的情况。
     *
     * 在您的代码中，当您将`superPackage`和`fastPackage`同时添加到`monthJson`和`yearJson`时，它们实际上形成了一个循环引用。为了处理这种情况，`JSONObject`类将循环引用转换为特殊的引用标记，即`$ref`。
     *
     * 具体地说，当您将`superPackage`添加到`yearJson`时，它会检测到它已经存在于`monthJson`中，因此不会将完整的对象添加到`yearJson`中，而是创建一个特殊的引用标记`{"$ref":"$.month.superPackage"}`。这表示`yearJson`中的`superPackage`属性引用了`monthJson`中的`superPackage`属性。
     *
     * 同样地，当您将`fastPackage`添加到`yearJson`时，它也会创建一个特殊的引用标记`{"$ref":"$.month.fastPackage"}`，表示`yearJson`中的`fastPackage`属性引用了`monthJson`中的`fastPackage`属性。
     *
     * 这种处理方式是为了避免无限递归和循环引用导致的问题，并保证 JSON 对象的正确性。当您使用`JSONObject`的`toString()`方法将其转换为字符串时，会自动应用这种引用标记的处理机制。
     *
     * 所以最终输出的 JSON 字符串中，`yearJson`中的`superPackage`属性和`fastPackage`属性被替换为了对`monthJson`中相应属性的引用标记`{"$ref":"$.month.superPackage"}`和`{"$ref":"$.month.fastPackage"}`，以表示它们与`monthJson`中的属性是相同的对象。
     */
    @GetMapping("/t34")
    public void test34(){
        JSONObject jsonObject = new JSONObject();

        JSONObject monthJson = new JSONObject();
        JSONObject yearJson = new JSONObject();

        JSONObject superPackage = new JSONObject();
        JSONObject fastPackage = new JSONObject();

        JSONObject monthSuperPackage = new JSONObject();
        JSONObject monthFastPackage = new JSONObject();

        monthJson.put("superPackage", monthSuperPackage);
        monthJson.put("fastPackage", monthFastPackage);
        yearJson.put("superPackage", superPackage);
        yearJson.put("fastPackage", fastPackage);

        jsonObject.put("year", yearJson);
        jsonObject.put("month", monthJson);

        System.out.println(JSON.toJSONString(jsonObject));
    }

    @GetMapping("t35")
    public void test35(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("1111111111111");

        List<UserEntity> list = userMapper.getUserList(userEntity);

        System.out.println(list.size());

        UserEntity userById = userMapper.getUserById(1212);
        System.out.println(userById.getUserName());
    }

    @GetMapping("t36")
    public void test36(){
        String s = "202312";

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        try {
            Date date = format.parse(s); // 将字符串解析为日期对象

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            calendar.add(Calendar.MONTH, 1); // 添加一个月

            Date nextMonth = calendar.getTime();
            String nextMonthString = format.format(nextMonth); // 将日期对象格式化为字符串

            System.out.println("输入的年月：" + s);
            System.out.println("下一个月：" + nextMonthString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("t37")
    public void test37(){
        // 生成一个uuid
        System.out.println(UUID.randomUUID().toString());
    }

    @GetMapping("t38")
    public void test38() {
        String s = "Hello World";
        // 使用hutool工具类对s进行md5加密，取32位小写
        String s1 = SecureUtil.md5(s);
        System.out.println(s1);
    }

    @GetMapping("t39")
    public void test39(){
        System.out.println("====hello\t==== \n===world\t====");
    }

    @GetMapping("t40")
    public void test40(){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("1", "1");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("2", "2");

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("3", "3");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);

        System.out.println("jsonArray为：" + jsonArray);

        // 创建一个新的JSONArray，并把jsonArray赋值给它
        JSONArray array = jsonArray;
        // 反转新的JSONArray
        Collections.reverse(array);

        System.out.println("此时的jsonArray为：" + jsonArray);
    }

    @GetMapping("t41")
    public void test41(){
        String s = "{\n" +
                "    \"userName\": \"langjialingbojue\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"age\": 20\n" +
                "}";

        UserEntity userEntity = JSON.parseObject(s, UserEntity.class);
        System.out.println(userEntity.getUserName());
        System.out.println(userEntity.getPassword());
        System.out.println(userEntity.getId());
    }

    @GetMapping("t42")
    public void test42(){
        String s = "[1,2,3,4,5]";
        List<Integer> list = JSON.parseArray(s, Integer.class);
        System.out.println(list.size());
        System.out.println(list.get(2));
    }

    @GetMapping("t43")
    public void test43(){
        // 匹配元素：使用anyMatch()方法判断列表中是否存在满足指定条件的元素。
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        boolean anyEven = numbers1.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("anyEven:" + anyEven);

        // 匹配元素：使用allMatch()方法判断列表中是否所有元素都满足指定条件。
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
        boolean allEven = numbers2.stream().allMatch(n -> n % 2 == 0);
        System.out.println("allEven:" + allEven);

        // 归约操作：使用reduce()方法将列表中的元素进行归约操作，例如求和或求最大值。
        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers3.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum:" + sum);

        // 收集结果：使用collect()方法将流中的元素收集到集合或其他数据结构中。
        List<Integer> numbers4 = Arrays.asList(1, 2, 3, 4, 5);
        Set<Integer> numberSet = numbers4.stream().collect(Collectors.toSet());
        System.out.println(numberSet);

        // 去重元素：使用distinct()方法去除列表中的重复元素。
        List<Integer> numbers5 = Arrays.asList(1, 2, 2, 3, 3, 4, 5);
        List<Integer> distinctNumbers = numbers5.stream().distinct().collect(Collectors.toList());
        System.out.println("distinctNumbers:" + distinctNumbers);

        // 丢弃元素：使用dropWhile()方法丢弃列表中满足指定条件的元素，直到条件不再满足为止。
        List<Integer> numbers6 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> droppedNumbers = numbers6.stream().dropWhile(n -> n < 3).collect(Collectors.toList());
        System.out.println("droppedNumbers:" + droppedNumbers);

    }

    @GetMapping("t44")
    public void test44(){
        // 创建一个整数列表
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        // 使用Lambda表达式进行列表筛选和计算平方和
        int sumOfSquares = numbers.stream()          // 将列表转换为流
                // 筛选偶数
                .filter(n -> n % 2 == 0)
                // 计算每个数的平方
                .map(n -> n * n)
                // 计算平方和
                .reduce(0, Integer::sum);

        // 打印结果
        System.out.println("平方和: " + sumOfSquares);
    }

    @GetMapping("t45")
    public void test45(){
        MathOperation mathOperation = (int a, int b, int c) -> a + b + c;
        System.out.println(mathOperation.operation(1, 2, 3));

        StringOperation stringOperation = (String s, String s1, String s2) -> {
            String ss = s + s1;
            return ss + s1 + s2;
        };
        System.out.println(stringOperation.operation("Hello", "World", "!"));
    }

    @RequestMapping(value = "t46/{id}/{name}", method = RequestMethod.GET)
    public String test46(@PathVariable(value = "name") String id, @PathVariable(value = "id") String name){
        return "id为：" + id + "，name为：" + name;
    }

    interface MathOperation {
        int operation(int a, int b, int c);
    }

    interface StringOperation {
        String operation(String s, String s1, String s2);
    }

    @PostMapping("t47")
    public void test47(@RequestBody @Valid UserEntity userEntity){
        System.out.println(userEntity.getUserName());
        System.out.println(userEntity.getPassword());
        System.out.println(userEntity.getId());

        Map<String, String> map = new HashMap<>(10);
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, "value" + i);
        }
        System.out.println(map);
    }
    
    @GetMapping("/t48")
    public void test48(){
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(null);
        System.out.println(jsonArray);
    }

    @GetMapping("/t49")
    public void test49(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next == 3){
                break;
            }
            iterator.remove();
        }
        System.out.println(list);

        for (Integer item : list) {
            if (item == 3){
                list.remove(item);
            }
        }
    }

    @GetMapping("/t50")
    public long test50(){
        UserEntity userEntity = new UserEntity();
        System.out.println(userEntity.getAge());
        System.out.println(userEntity.getPassword());

        userEntity.setAge(30);
        System.out.println(userEntity.getAge());
        return userEntity.getAge();
    }
    
    @GetMapping("/t51")
    public void test51(){
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(25);

        System.out.println(userEntity);

        handleList(userEntity);

        System.out.println(userEntity);
    }

    private void handleList(UserEntity userEntity) {
        userEntity.setAge(30);
    }

    @GetMapping("/t52")
    public void test52(){
        UserEntity userEntity = new UserEntity();
        System.out.println("userEntity的初始值：" + userEntity);

        userEntity.setAge(30);
        System.out.println("在业务逻辑中对userEntity再次赋值：" + userEntity);
    }

    @GetMapping("/t53")
    public void test53(){
        List<Integer> l = Stream.of(1,2).collect(Collectors.toList());

        l.add(3);
        System.out.println(l);
        // 字符串型
        System.out.println(Stream.of("lang", "jia", "ling").collect(Collectors.toList()));

        List<Integer> list0 = Arrays.asList(1,2,3);
        list0.add(4);
        System.out.println(list0);
        List<String> list = Arrays.asList("apple", "banana", "orange");
    }

}
