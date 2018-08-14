package org.luncert.modelMethod;

public class HummerH2 extends HummerModel {

	@Override
	public void start() {
		System.out.println("H2 start ...");
	}

	@Override
	public void stop() {
		System.out.println("H2 stop ...");
	}

	@Override
	public void alarm() {
		System.out.println("H2 alarm ...");
	}

	@Override
	public void engineBoom() {
		System.out.println("H2 boom ...");
	}

}