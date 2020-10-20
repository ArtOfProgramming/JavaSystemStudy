package com.daiwei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BService {

    @Autowired
    private AService aService;

    public void testAopB() {
        System.out.println("BService");
    }
}
