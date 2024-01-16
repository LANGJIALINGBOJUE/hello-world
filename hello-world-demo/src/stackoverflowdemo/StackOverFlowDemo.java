package stackoverflowdemo;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/16 10:01
 */
public class StackOverFlowDemo {
    public static int count = 0;
    public static void main(String[] args) {
        recursion();
    }

    public static void recursion(){
        System.out.println(++count);
        recursion();
    }
}
