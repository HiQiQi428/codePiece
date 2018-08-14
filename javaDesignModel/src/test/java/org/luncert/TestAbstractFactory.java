package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.abstractFactory.Human;
import org.luncert.abstractFactory.HumanFactory;
import org.luncert.abstractFactory.ManFactory;

@RunWith(JUnit4.class)
public class TestAbstractFactory {

    @Test
    public void test() {
        HumanFactory factory = new ManFactory();
        Human human = factory.createHuman();
        human.say();
    }
    
}