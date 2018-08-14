package org.luncert.statusModel;

public class RunningState implements LiftState {

	@Override
	public void open() {
		// do nothing
	}

	@Override
	public void close() {
		// do nothing
	}

	@Override
	public void run() {
		System.out.println("Elevator running");
	}

	@Override
	public void stop() {
	}

}