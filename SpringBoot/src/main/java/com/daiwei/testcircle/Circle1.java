package com.daiwei.testcircle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Circle1 {

    @Autowired
    private Circle2 circle2;


    // 会导致循环依赖
//    @Autowired
//    public Circle1(Circle2 circle2) {
//        this.circle2 = circle2;
//    }
}
