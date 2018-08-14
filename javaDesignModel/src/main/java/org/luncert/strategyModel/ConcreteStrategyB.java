package org.luncert.strategyModel;

public class ConcreteStrategyB implements Strategy {

	@Override
	public void search() {
		System.out.println("search with B-engine");
	}

}