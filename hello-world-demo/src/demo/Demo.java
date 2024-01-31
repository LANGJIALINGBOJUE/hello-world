package demo;

import java.util.HashMap;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/16 10:02
 */
public class Demo {
    public static void main(String[] args) {
         test("hello");
    }

    private static String test(String s){

        try{
            return s;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("finally代码块：" + s);
        }

        return null;
    }
}
