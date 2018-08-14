package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.strategyModel.ConcreteStrategyA;
import org.luncert.strategyModel.Context;
import org.luncert.strategyModel.Strategy;

@RunWith(JUnit4.class)
public class TestStrategy {

    @Test
    public void test() {
        Strategy strategy = new ConcreteStrategyA();
        Context ctx = new Context(strategy);
        ctx.search();
    }
    
}