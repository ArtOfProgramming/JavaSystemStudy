package com.daiwei;

import com.daiwei.bean.MyComponent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@MyComponent(beanInfos = {"com.daiwei.bean.Person:name=daiwei:age=18", "com.daiwei.bean.Cat:name=jiafeimao:age=3"})
public class SelfStruct  {

    private static  ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();

    public SelfStruct(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String beanDefination = "";
            Class<SelfStruct> selfStructClass = SelfStruct.class;
            MyComponent myComponent = selfStructClass.getAnnotation(MyComponent.class);
            for (String s : myComponent.beanInfos()) {
//            while ((beanDefination = bufferedReader.readLine()) != null) {
                String[] beanDefinations = s.split(":");

                Class cls = Class.forName(beanDefinations[0]);
                Field[] fields = cls.getDeclaredFields();
                Object o = Class.forName(beanDefinations[0]).getDeclaredConstructor().newInstance();

                for (int i = 1; i < beanDefinations.length; i++) {
                    for (Field field : fields) {
                        if (field.getName().equals(beanDefinations[i].split("=")[0])) {
                            field.setAccessible(true);
                            switch (field.getType().getName()) {
                                case "java.lang.String":
                                    field.set(o, beanDefinations[i].split("=")[1]);
                                    break;
                                case "int":
                                    field.set(o, Integer.valueOf(beanDefinations[i].split("=")[1]));
                                    break;
                                default:
                                    break;
                            }

                        }
                    }
                }
                int len = beanDefinations[0].split("\\.").length;
                hashMap.put(beanDefinations[0].split("\\.")[len - 1].toLowerCase(), o);
//            }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name) {
        return hashMap.get(name);
    }
}
