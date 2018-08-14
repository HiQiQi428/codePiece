package org.luncert.prototype.simple;

public class ConcretePrototypeA implements Prototype {
    
    @Override
    public Prototype clone() {
        return new ConcretePrototypeA();
    }

}