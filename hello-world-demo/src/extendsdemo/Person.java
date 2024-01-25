package extendsdemo;

/**
 * @author 郎家岭伯爵
 * @time 2024/1/15 10:53
 */
public class Person {

    protected int age;

    public Person(){
        System.out.println("Person 无参构造");
        System.out.println("Person age:" + age);

        toAge();
        System.out.println("Person toAge:" + age);
    }

    public Person(int age){
        System.out.println("Person:" + age);
    }

    public void say(){
        System.out.println("这是一个人。");
    }

    public Person toAge(){
        return null;
    }
}
