package org.luncert.prototype.simple;

public class ConcretePrototypeB implements Prototype {

    @Override
    public Prototype clone() {
        return new ConcretePrototypeB();
    }
    
}