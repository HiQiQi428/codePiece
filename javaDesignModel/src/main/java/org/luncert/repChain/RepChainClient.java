package org.luncert.repChain;

import java.util.ArrayList;
import java.util.List;

public class RepChainClient implements Ratify {

    private List<Ratify> ratifies;

    public RepChainClient() {
        ratifies = new ArrayList<>();
    }

    public void addRatify(Ratify ratify) { ratifies.add(ratify); }

	@Override
	public Result process(Request request) {
        List<Ratify> lst = new ArrayList<>();
        lst.addAll(ratifies);
        lst.add(new GroupLeader());
        lst.add(new Manager());
        lst.add(new DepartmentHeader());

        Chain chain = new Chain(lst);
        return chain.process(request);
	}

}