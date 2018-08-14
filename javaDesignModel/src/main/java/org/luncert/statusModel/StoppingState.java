package org.luncert.statusModel;

public class StoppingState implements LiftState {

	@Override
	public void open() {
	}

	@Override
	public void close() {
	/// do nothing
	}

	@Override
	public void run() {
	}

	@Override
	public void stop() {
		System.out.println("Elevator stoped");
	}

}