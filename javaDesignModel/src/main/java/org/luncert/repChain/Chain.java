package org.luncert.repChain;

import java.util.List;

public class Chain implements Ratify {

    private List<Ratify> ratifies;

    public Chain(List<Ratify> ratifies) {
        this.ratifies = ratifies;
    }

	@Override
	public Result process(Request request) {
        Result result = null;
        for (Ratify ratify : ratifies) {
            result = ratify.process(request);
            if (result != null) return result;
        }
        return result;
	}

}