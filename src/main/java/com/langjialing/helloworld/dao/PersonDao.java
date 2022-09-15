package com.langjialing.helloworld.dao;

import com.langjialing.helloworld.entity.Person;

public class PersonDao {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(20);
        person.setName("xiaoming");
        System.out.println(person.toString());
    }
}
