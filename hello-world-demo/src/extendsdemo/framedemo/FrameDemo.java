package extendsdemo.framedemo;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/15 17:23
 */
public class FrameDemo {
    public static void main(String[] args) {
        System.out.println("main方法执行。。");
        A();
    }

    public static void A(){
        System.out.println("A方法执行。。");
        B();
    }

    public static void B(){
        System.out.println("B方法执行。。");
        C();
    }

    public static void C(){
        System.out.println("C方法执行。。");
        throw new RuntimeException("测试");
    }
}
