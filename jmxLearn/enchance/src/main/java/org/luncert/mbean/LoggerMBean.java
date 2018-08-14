package org.luncert.mbean;

public interface LoggerMBean {

    public void setLogLevel(int level);

    public int getLogLevel();

    public void writeLog(int logLevel, String message);

}