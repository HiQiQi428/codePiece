package org.luncert.repChain;

public class Result {

    private boolean isRatify;
    private String info;

    public Result(boolean isRatify, String info) {
        this.isRatify = isRatify;
        this.info = info;
    }

    public boolean isRatify() { return isRatify; }

    public void setRatify(boolean isRatify) { this.isRatify = isRatify; }

    public String getInfo() { return info; }

    public void setInfo(String info) { this.info = info; }

    @Override
    public String toString() {
        return "Result [isRatify = " + isRatify + ", info = " + info + "]";
    }
}