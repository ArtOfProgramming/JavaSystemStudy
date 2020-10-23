package com.daiwei.testcircle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Circle2 {

    @Autowired
    private Circle3 circle3;
}
