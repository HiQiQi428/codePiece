package org.luncert.responsibilityChain;

import java.util.List;

public class RealChain implements Ratify.Chain {

    private Request request;

    private List<Ratify> ratifyList;

    private int index;

    public RealChain(List<Ratify> ratifyList, Request request, int index) {
        this.ratifyList = ratifyList;
        this.request = request;
        this.index = index;
    }

	@Override
	public Request request() {
		return request;
	}

	@Override
	public Result proceed(Request request) {
        Result result = null;
        if (ratifyList.size() > index) {
            RealChain realChain = new RealChain(ratifyList, request, index + 1);
            Ratify ratify = ratifyList.get(index);
            result = ratify.deal(realChain);
        }
		return result;
	}

}