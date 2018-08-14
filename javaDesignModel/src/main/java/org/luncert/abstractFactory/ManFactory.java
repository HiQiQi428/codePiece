package org.luncert.abstractFactory;

public class ManFactory implements HumanFactory {

	@Override
	public Human createHuman() {
		return new Man();
	}
    
}