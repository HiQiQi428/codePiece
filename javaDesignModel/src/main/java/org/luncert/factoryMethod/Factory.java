package org.luncert.factoryMethod;

public abstract class Factory {
    
    public abstract <T extends Product> T createProduct(Class<T> clazz);
    
}