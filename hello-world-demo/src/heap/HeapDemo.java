package heap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/16 14:26
 */
public class HeapDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Object> list = new ArrayList<>();
        System.in.read();
        while (true){
            list.add(new byte[1024 * 1024 * 100]);
            System.out.println("添加一次");
            Thread.sleep(500);
        }
    }
}
