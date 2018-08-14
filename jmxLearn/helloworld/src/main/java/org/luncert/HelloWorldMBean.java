package org.luncert;

public interface HelloWorldMBean {

    public void setGreeting(String greeting);

    public String getGreeting();

    public String printGreeting(String param);

}