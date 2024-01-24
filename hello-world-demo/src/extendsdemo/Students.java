package extendsdemo;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/15 10:54
 */
public class Students extends Person{

    public Students() {
    }

    public Students(int age) {
        super.age = age;
        this.age = age + 5;
    }

    @Override
    public void say(){
        System.out.println("这不仅是一个人，还是一个学生。");
        System.out.println("Student age:" + this.age);
    }
}
