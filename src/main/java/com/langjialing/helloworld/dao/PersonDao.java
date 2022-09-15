package com.langjialing.helloworld.dao;

import com.langjialing.helloworld.entity.Person;

public class PersonDao {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(20);
        person.setName("小明");
        System.out.println(person.toString());
        System.out.println("中文注释");
    }
}
