package com.daiwei.bean;
/**
 * 测试annotation
 *
 * @author daiwei
 * @version 1.0
 * @since 1.5
 */
public class AnnotationDemo1 {

    /**
     * 
     * @param food 食物
     */
    public void eat(String food) {
        System.out.println("eat" + food);
    }

    /**
     *
     * @param a 参数1
     * @param b 参数2
     * @return 计算结果
     */
    public int add(int a, int b) {
        return a + b;
    }
}
