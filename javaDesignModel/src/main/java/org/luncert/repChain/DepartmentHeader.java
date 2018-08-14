package org.luncert.repChain;

public class DepartmentHeader implements Ratify {

	@Override
	public Result process(Request request) {
        System.out.println("DepartmentHeader => request: " + request.toString());
        if (request.getDays() > 7) return new Result(false, "你这个完全没必要");
		else return new Result(true, "DepartmentHeader: 不要着急，把事情处理完再回来");
	}

}