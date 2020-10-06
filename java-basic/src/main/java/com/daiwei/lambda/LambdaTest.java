package com.daiwei.lambda;

import com.daiwei.lambda.bean.Calculator;
import com.daiwei.lambda.bean.Cook;
import com.daiwei.lambda.bean.Person;
import java.util.Arrays;
import java.util.Comparator;

public class LambdaTest {

    public static void main(String[] args) {
        testCook(() -> {
                System.out.println("做饭");
            }
        );
        cal(1,3, (int a, int b) -> {
            return a + b;
        });
    }

    public  static void cal(int a, int b, Calculator c) {
        int sum = c.cal(a, b);
        System.out.println(sum);
    }

    public static void testCook (Cook cook) {
        cook.makeFood();

        Person[] people = new Person[] {
            new Person("柳岩", 28),
            new Person("代维" , 18),
            new Person("你好", 19)
        };

        Arrays.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        for (Person person : people) {
            System.out.println(person.toString());
        }

        Arrays.sort(people, (Person o1, Person o2) -> {
            return o2.getAge() - o1.getAge();
        });

        for (Person person : people) {
            System.out.println(person.toString());
        }
    }
}
