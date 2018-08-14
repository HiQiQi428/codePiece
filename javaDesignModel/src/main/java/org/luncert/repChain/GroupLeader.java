package org.luncert.repChain;

public class GroupLeader implements Ratify {

	@Override
	public Result process(Request request) {
        System.out.println("GroupLeader => request: " + request.toString());

        if (request.getDays() > 1) {
            request.setInfo(request.getName() + "平时表现不错，而且现在项目也不忙");
            return null;
        }
		else return new Result(true, "GroupLeader: 早去早回");
	}

}