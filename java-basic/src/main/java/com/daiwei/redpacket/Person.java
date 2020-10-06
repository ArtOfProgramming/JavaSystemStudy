package com.daiwei.redpacket;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Person {

    String name;
    int age;
    Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String FormatDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 1);
        c.add(Calendar.MONTH, 2);
        c.get(Calendar.MONTH);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-yy");
        System.currentTimeMillis();
        return simpleDateFormat.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return age == person.age &&
            Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
}
