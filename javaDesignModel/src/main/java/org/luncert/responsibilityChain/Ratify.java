package org.luncert.responsibilityChain;

public interface Ratify {

    public Result deal(Chain chain);

    interface Chain {
        Request request();

        Result proceed(Request request);
    }

}