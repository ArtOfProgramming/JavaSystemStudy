package com.daiwei.mymapping;

import java.util.HashMap;

public class MyMapping {

    public static HashMap<String, String> mapping = new HashMap<>();

    static {
        mapping.put("/mytomcat", "com.daiwei.servlet.MyServlet");
    }

    public HashMap<String, String> getMapping() {
        return mapping;
    }
}
