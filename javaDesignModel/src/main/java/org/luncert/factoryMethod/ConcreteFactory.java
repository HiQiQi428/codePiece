package org.luncert.factoryMethod;

public class ConcreteFactory extends Factory {

	@Override
	public <T extends Product> T createProduct(Class<T> clazz) {
        try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
		}
	}
    
}