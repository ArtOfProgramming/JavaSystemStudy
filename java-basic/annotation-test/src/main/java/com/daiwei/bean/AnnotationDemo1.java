package com.daiwei.bean;
/**
 * ����annotation
 *
 * @author daiwei
 * @version 1.0
 * @since 1.5
 */
public class AnnotationDemo1 {

    /**
     * 
     * @param food ʳ��
     */
    public void eat(String food) {
        System.out.println("eat" + food);
    }

    /**
     *
     * @param a ����1
     * @param b ����2
     * @return ������
     */
    public int add(int a, int b) {
        return a + b;
    }
}
