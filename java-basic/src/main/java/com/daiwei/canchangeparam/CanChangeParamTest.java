package com.daiwei.canchangeparam;


import com.daiwei.canchangeparam.bean.Cat;
import com.daiwei.canchangeparam.bean.Person;

public class CanChangeParamTest {

    public static void main(String[] args) {
        Person person = new Person();
        Cat cat = new Cat();
        Test();
    }

    public static void Test(Object... arr) {
        for (Object o : arr) {
            if (o instanceof Person) {
                Person person = (Person) o;
                person.talk();
            } else if (o instanceof Cat) {
                Cat cat = (Cat) o;
                ((Cat) o).talk();
            }
        }
    }
}
