package org.luncert.abstractFactory;

public class WomanFactory implements HumanFactory {

	@Override
	public Human createHuman() {
		return new Woman();
	}
    
}