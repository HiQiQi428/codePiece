package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.interpreterModel.Calculator;

@RunWith(JUnit4.class)
public class TestIntercepter {

    @Test
    public void test() {
        Calculator calculator = new Calculator("123 + 124 + 125 + 126");
        System.out.println(calculator.calculate());
    }
    
}