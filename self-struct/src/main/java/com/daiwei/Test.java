package com.daiwei;

import com.daiwei.bean.Person;
import com.daiwei.bean.ResourcePath;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String path = URLDecoder.decode(new Test().getClass().getResource("/properties.txt").getPath(), "UTF-8");
        SelfStruct selfStruct = new SelfStruct(path);
        Person person = (Person) selfStruct.getBean("person");
        System.out.println(person.toString());
        System.out.println(selfStruct.getBean("cat").toString());
    }


}
