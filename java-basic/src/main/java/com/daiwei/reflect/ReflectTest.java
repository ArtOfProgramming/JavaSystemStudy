package com.daiwei.reflect;

import com.daiwei.reflect.bean.Person;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectTest {

    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("com.daiwei.bean.Person");

        // Field
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        Person person = new Person();
        field.set(person, "daiwei");
        System.out.println(field.get(person));
        System.out.println(field.getType().toString());
        System.out.println(cls.getDeclaredField("age").getType());

        // Constructor
//        System.out.println(cls.getFields());
//        System.out.println(cls.getConstructors());
        Constructor constructor = cls.getConstructor(String.class, int.class);
        Person person1 = (Person) constructor.newInstance("daiwei", 18);
        System.out.println(person1);
        System.out.println(cls.getDeclaredConstructor().newInstance());

        // Method
        System.out.println(cls.getMethods());
        cls.getMethod("talk", String.class).invoke(person1, "你好啊");
        // className
        System.out.println(cls.getName());
    }
}
