package extendsdemo;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/15 10:54
 */
public class Students extends Person{

    public Students() {
        // 这里Java会隐式地调用Person的无参构造
    }


    public Students(int age) {
        // 显式地调用Person的有参构造
        super(age);
        this.age = age + 5;
    }

    @Override
    public void say(){
        System.out.println("这不仅是一个人，还是一个学生。");
        System.out.println("Student age:" + this.age);
        System.out.println("Student super age:" + super.age);
    }

    @Override
    public Students toAge(){
        return this;
    }
}
