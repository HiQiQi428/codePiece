package org.luncert.interpreterModel;

import java.util.Stack;

public class Calculator {

    protected Stack<ArithmeticExpression> arithmeticExpressionStack = new Stack<>();

    public Calculator(String expression) {
        ArithmeticExpression arithmeticExpression1, arithmeticExpression2;
        String[] elements = expression.split(" ");
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].charAt(0) == '+') {
                arithmeticExpression1 = arithmeticExpressionStack.pop();
                arithmeticExpression2 = new NumExpression(Integer.valueOf(elements[++i]));
                arithmeticExpressionStack.push(new AdditionExpression(arithmeticExpression1, arithmeticExpression2));
            }
            else {
                arithmeticExpressionStack.push(new NumExpression(Integer.valueOf(elements[i])));
            }
        }
    }

    public int calculate() {
        return arithmeticExpressionStack.pop().interpret();
    }

}