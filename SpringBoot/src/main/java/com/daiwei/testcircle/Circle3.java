package com.daiwei.testcircle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Circle3 {

    @Autowired
    private Circle1 circle1;
}
