package com.daiwei.bean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest  {

    @Before
    public void init() {
        System.out.println("准备测试");
    }

    @After
    public void close() {
        System.out.println("释放资源");
    }

    @Test
    public void calAddTest() {
        System.out.println("开始测试add");
        Assert.assertEquals(3, new Calculator().add(1, 2));
    }

    @Test
    public void calSubTest() {
        System.out.println("开始测试sub");
        Assert.assertEquals(0, new Calculator().sub(1, 1));
    }
}
