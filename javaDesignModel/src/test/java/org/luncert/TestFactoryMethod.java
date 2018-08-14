package org.luncert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.factoryMethod.ConcreteFactory;
import org.luncert.factoryMethod.ConcreteProductA;
import org.luncert.factoryMethod.Factory;
import org.luncert.factoryMethod.Product;

@RunWith(JUnit4.class)
public class TestFactoryMethod {

    @Test
    public void test() {
        Factory factory = new ConcreteFactory();
        Product product = factory.createProduct(ConcreteProductA.class);
        product.method();
    }
    
}