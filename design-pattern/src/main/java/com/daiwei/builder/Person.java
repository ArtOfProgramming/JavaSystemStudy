package com.daiwei.builder;

/**
 * 建造者模式 使用：配置类
 */
public class Person {

    private String name;
    private int age;
    private int height;
    private int weight;
    private Location location;

    public static class PersonBuilder {

        Person person = new Person();

        public PersonBuilder basicInfo(String name, int age) {
            person.name = name;
            person.age = age;
            return this;
        }

        public PersonBuilder weight(int weight) {
            person.weight = weight;
            return this;
        }

        public PersonBuilder height(int height) {
            person.weight = height;
            return this;
        }

        public PersonBuilder location(long phone, String address) {
            person.location = new Location(phone, address);
            return this;
        }

        public Person build() {
            return person;
        }

    }


    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", height=" + height +
            ", weight=" + weight +
            ", location=" + location +
            '}';
    }

    public static void main(String[] args) {
        Person person = new PersonBuilder().basicInfo("daiwei", 18).height(180).weight(65).location(15345441423L, "地球").build();
        System.out.println(person.toString());
    }
}

class Location {

    private long phone;
    private String address;

    public Location(long phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
            "phone=" + phone +
            ", address='" + address + '\'' +
            '}';
    }
}