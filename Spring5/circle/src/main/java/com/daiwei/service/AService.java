package com.daiwei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AService {

    @Autowired
    private BService bService;

    public void testAopA() {
        System.out.println("AService");
    }
}
