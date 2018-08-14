package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.prototype.register.ConcretePrototypeA;
import org.luncert.prototype.register.ConcretePrototypeB;
import org.luncert.prototype.register.Prototype;
import org.luncert.prototype.register.PrototypeManager;

@RunWith(JUnit4.class)
public class TestPrototype {

    @Test
    public void test() throws Exception {
        Prototype p1 = new ConcretePrototypeA();
        PrototypeManager.setPrototype("p1", p1);
        
        Prototype p3 = PrototypeManager.getPrototype("p1").clone();
        p3.setName("p3-name");
        
        Prototype p2 = new ConcretePrototypeB();
        PrototypeManager.setPrototype("p1", p2);

        Prototype p4 = PrototypeManager.getPrototype("p1").clone();
        p4.setName("p4-name");

        PrototypeManager.removePrototype("p1");
    }
}