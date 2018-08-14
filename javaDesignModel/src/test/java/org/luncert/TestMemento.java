package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.memento.CareTaker;
import org.luncert.memento.Emp;

@RunWith(JUnit4.class)
public class TestMemento {

    @Test
    public void test() {
        CareTaker taker = new CareTaker();

        Emp emp = new Emp("luncert", 20, 10000);
        System.out.println(emp);

        taker.setMemento(emp.memento());

        emp.setAge(19);
        emp.setSaraly(0);
        System.out.println(emp);

        emp.recovery(taker.getMemento());
        System.out.println(emp);
    }
    
}