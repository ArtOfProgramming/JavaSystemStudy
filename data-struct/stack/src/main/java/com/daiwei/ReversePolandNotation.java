package com.daiwei;

import com.sun.corba.se.impl.orbutil.StackImpl;
import com.sun.deploy.util.ArrayUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReversePolandNotation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入一个中缀表达式：");
            String infixExpression = scanner.nextLine();
            String ReversePolandNotation = infixToReversePoland(infixExpression);
            String[] arr = ReversePolandNotation.split(" ");
             StackImpl stack = new StackImpl();
            for (int i = 0; i < arr.length; i++) {
                if (isOperation(arr[i])) {
                    int temp1 = (int)stack.pop();
                    int temp2 = (int)stack.pop();
                    int temp3 = cal(temp2, temp1, arr[i]);
                    stack.push(temp3);
                } else {
                    stack.push(Integer.valueOf(arr[i]));
                }
            }
            System.out.println(stack.pop());
        }
    }

    public static boolean isOperation(String a) {
        return a.equals("+") || a.equals("-") || a.equals("*") || a.equals("/");
    }

    public static int cal(int num1, int num2, String operation) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                break;
        }
        return 0;
    }

    public static int priority(String operation) {
        switch (operation) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            default:
                return -1;
        }
    }

    public static String infixToReversePoland(String expression) {
        String reversePoland = "";
        String[] temp = expression.split(" ");
        List<String> tempList = new ArrayList<>();
        StackImpl operationStack = new StackImpl();
        for (int i = 0; i < temp.length; i++) {
            if (isOperation(temp[i])) {
                if (operationStack.empty() || priority(temp[i]) > priority((String)operationStack.peek())) {
                    operationStack.push(temp[i]);
                    continue;
                }
                while (true) {
                    if (!operationStack.empty()) {
                        if (((String)operationStack.peek()).equals("(") || priority(temp[i]) > priority((String)operationStack.peek())) {
                            operationStack.push(temp[i]);
                            break;
                        } else {
                            tempList.add((String)operationStack.pop());
                        }
                    } else {
                        operationStack.push(temp[i]);
                        break;
                    }
                }
            } else if (temp[i].equals("(")) {
                operationStack.push(temp[i]);
            } else if (temp[i].equals(")")) {
                while (true) {
                    if (!((String)operationStack.peek()).equals("(")) {
                        tempList.add((String)operationStack.pop());
                    } else {
                        operationStack.pop();
                        break;
                    }
                }
            } else {
                tempList.add(temp[i]);
            }
        }
        while (!operationStack.empty()) {
            tempList.add((String)operationStack.pop());
        }
        for (String s : tempList) {
            reversePoland += s + " ";
        }
        return reversePoland;
    }
}
