package org.luncert.responsibilityChain;

import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsibilityClient {

    private List<Ratify> ratifies;

    public ChainOfResponsibilityClient() {
        ratifies = new ArrayList<>();
    }

    public void addRatify(Ratify ratify) { ratifies.add(ratify); }

    public Result execute(Request request) {
        List<Ratify> lst = new ArrayList<>();
        lst.addAll(ratifies);
        lst.add(new GroupLeader());
        lst.add(new Manager());
        lst.add(new DepartmentHeader());

        RealChain realChain = new RealChain(lst, request, 0);
        return realChain.proceed(request);
    }

}