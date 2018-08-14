package org.luncert.repChain;

public class Manager implements Ratify {

	@Override
	public Result process(Request request) {
        System.out.println("Manager => request: " + request.toString());

        if (request.getDays() > 1) {
            request.setInfo(request.getName() + "每月的KPI考核还不错，可以批准");
            return null;
        }
		else return new Result(true, "GroupLeader: 早去早回");
	}

}