package com.daiwei;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThrowableTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = dateFormat.parse("1999-12-10");
        System.out.println(date);
        try {

            throw new Error("Hello");
        } catch (Error e) {
            System.out.println(e);
        }
    }
}
