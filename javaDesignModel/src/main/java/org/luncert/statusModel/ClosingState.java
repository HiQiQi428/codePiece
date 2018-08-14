package org.luncert.statusModel;

public class ClosingState implements LiftState {

	@Override
	public void open() {
	}

	@Override
	public void close() {
		System.out.println("Elevator closed");
	}

	@Override
	public void run() {
		
	}

	@Override
	public void stop() {
	}

}