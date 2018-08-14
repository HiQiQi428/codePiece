package org.luncert.commandModel;

public class RebootCommand implements Command {

	private Receiver receiver;

	public RebootCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void exec() {
		receiver.actoin();
	}

}