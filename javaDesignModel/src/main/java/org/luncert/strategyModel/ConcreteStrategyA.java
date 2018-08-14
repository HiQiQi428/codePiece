package org.luncert.strategyModel;

public class ConcreteStrategyA implements Strategy {

	@Override
	public void search() {
		System.out.println("search with A-engine");
	}

}