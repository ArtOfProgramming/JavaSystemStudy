package com.daiwei.canchangeparam;


public class ParamTest {

    private String name;


    public ParamTest() {
    }

    public ParamTest(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        ParamTest paramTest = new ParamTest("hello");
        test1(paramTest);
        // 作用域问题
        System.out.println("2:" + paramTest);

    }

    public static void test1(ParamTest paramTest) {
        paramTest = null;
        System.out.println("1:" + paramTest);
    }

    public void testv1 () {

    }
}

class ParamExtend1 extends ParamTest {
    int a;

    public ParamExtend1() {

    }

    @Override // 可有可无,建议有
    public void testv1() {
        System.out.println("testv1");
    }
}
class ParamExtend2 extends ParamTest {
    int a;

    public ParamExtend2() {

    }

    @Override // 可有可无,建议有
    public void testv1() {
        System.out.println("testv2");
        System.out.println();
        A.hello();
        B b = new B();
        System.out.println(b.c);
    }

    static class A {
        private static int q;
        private static void hello() {
            int c = 2;
            class MethodClass {
                public void test() {
                    System.out.println(c);
                }
            }
            MethodClass m = new MethodClass();
            m.test();
        };
    }

    class B {
        private int c;

    }
}