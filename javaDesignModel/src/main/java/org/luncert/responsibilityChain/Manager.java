package org.luncert.responsibilityChain;

public class Manager implements Ratify {

	@Override
	public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println("Manager => request: " + request.toString());
        if (request.getDays() > 3) {
            Request newRequest = new Request.Builder().newRequest(request).setManagerInfo(request.getName() + "每月的KPI考核还不错，可以批准").build();
            return chain.proceed(newRequest);
        }
		else return new Result(true, "Manager: 早点吧事情办完，项目离不开你");
	}

}