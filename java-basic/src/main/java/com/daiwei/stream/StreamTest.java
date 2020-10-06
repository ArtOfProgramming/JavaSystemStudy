package com.daiwei.stream;

import com.daiwei.stream.bean.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
//        String[] arrays = new String[]{"代维", "迪丽热巴", "古力拉扎", "赵丽颖"};
//        Stream<String> stream = Arrays.stream(arrays);
////        Stream<String> stream1 = stream.filter((a) -> a.endsWith("维"));
////        System.out.println(stream);
////        System.out.println(stream1);
////        stream1.forEach((a) -> System.out.println(a));
//        stream.limit(10).forEach(a -> System.out.println(a));

        String[] arrays = new String[]{"代维", "迪丽热巴", "古力拉扎", "赵丽颖", "张三丰", "张无忌", "张敏"};
        Stream<String> stream = Arrays.stream(arrays);
        Stream<String> stream1 = Arrays.stream(arrays);
        Stream<String> stream2 = stream.filter((name) -> name.length() == 3).limit(3);
        Stream<String> stream3 = stream1.filter(name -> name.contains("张")).skip(2);
        ArrayList<Person> people = new ArrayList<>();
        Stream.concat(stream2, stream3).forEach((name) -> people.add(new Person(name)));
        for (Person person : people) {
            System.out.println(person.toString());
        }
    }
}
