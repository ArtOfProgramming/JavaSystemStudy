package com.daiwei.struct.stack;

import com.sun.corba.se.impl.orbutil.StackImpl;
import java.util.Scanner;

/**
 * 后缀表达式计算
 */
public class ParseExpression {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            StackImpl numberStack = new StackImpl();
            StackImpl operationStack = new StackImpl();
            System.out.println("请输入待计算的表达式：");
            String expression = scanner.next();
            String temp = "";
            for (int i = 0; i < expression.length(); i++) {
                char cur = expression.charAt(i);
                if (isOperation(cur)) {
                    numberStack.push(Integer.valueOf(temp));
                    if (operationStack.empty()) {
                        operationStack.push(cur);
                    } else if (priority(cur) <= priority((char)operationStack.peek())) {
                        int temp1 = (int)numberStack.pop();
                        int temp2 = (int)numberStack.pop();
                        int temp3 = cal(temp2, temp1, (char)operationStack.pop());
                        numberStack.push(temp3);
                        operationStack.push(cur);
                    } else {
                        operationStack.push(cur);
                    }
                    temp = "";
                } else {
                    temp += expression.charAt(i);
                }
//                switch (expression.charAt(i)) {
//                    case '+':
//                        numberStack.push(Integer.valueOf(temp));
//                        if (operationStack.empty()) {
//                            operationStack.push("+");
//                        } else {
//                            int temp1 = (int)numberStack.pop();
//                            int temp2 = (int)numberStack.pop();
//                            int temp3 = cal(temp2, temp1, (char)operationStack.pop());
//                            numberStack.push(temp3);
//                            operationStack.push("+");
//                        }
//                        temp = "";
//                        break;
//                    case '-':
//                        numberStack.push(Integer.valueOf(temp));
//                        if (operationStack.empty()) {
//                            operationStack.push("-");
//                        } else {
//                            int temp1 = (int)numberStack.pop();
//                            int temp2 = (int)numberStack.pop();
//                            int temp3 = cal(temp2, temp1, (char)operationStack.pop());
//                            numberStack.push(temp3);
//                            operationStack.push("-");
//                        }
//                        temp = "";
//                        break;
//                    case '*':
//                        numberStack.push(Integer.valueOf(temp));
//                        if (operationStack.empty()) {
//                            operationStack.push("*");
//                        } else {
//                            if (((char)operationStack.peek()).equals("/") || ((char)operationStack.peek()) == '*') {
//                                int temp1 = (int)numberStack.pop();
//                                int temp2 = (int)numberStack.pop();
//                                int temp3 = cal(temp2, temp1, (String)operationStack.pop());
//                                numberStack.push(temp3);
//                                operationStack.push("*");
//                            } else {
//                                operationStack.push("*");
//                            }
//                        }
//                        temp = "";
//                        break;
//                    case '/':
//                        numberStack.push(Integer.valueOf(temp));
//                        if (operationStack.empty()) {
//                            operationStack.push("/");
//                        } else {
//                            if (((String)operationStack.peek()).equals("*") || ((String)operationStack.peek()).equals("/")) {
//                                int temp1 = (int)numberStack.pop();
//                                int temp2 = (int)numberStack.pop();
//                                int temp3 = cal(temp2, temp1, (String)operationStack.pop());
//                                numberStack.push(temp3);
//                                operationStack.push("/");
//                            } else {
//                                operationStack.push("/");
//                            }
//                        }
//                        temp = "";
//                        break;
//                    default:
//                        temp += expression.charAt(i);
//                        break;
//                }
            }
            numberStack.push(Integer.valueOf(temp));
            while (true) {
                int temp1 = (int)numberStack.pop();
                if (numberStack.empty()) {
                    System.out.println("结果为：" + temp1);
                    break;
                }
                int temp2 = (int)numberStack.pop();
                numberStack.push(cal(temp2, temp1, (char) operationStack.pop()));
            }
        }

    }

    public static boolean isOperation(char a) {
        return a == '+' || a == '-' || a == '*' || a == '/';
    }

    public static int priority(char operation) {
        switch (operation) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            default:
                return -1;
        }
    }

    public static int cal(int num1, int num2, char operation) {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                break;
        }
        return 0;
    }
}
