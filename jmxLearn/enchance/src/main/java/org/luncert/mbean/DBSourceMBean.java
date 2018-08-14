package org.luncert.mbean;

import java.sql.Connection;

public interface DBSourceMBean {
    
    public void resetDataSource(String name);
    public void setAutoCommit(boolean autoCommit);
    public boolean getAutoCommit();
    public Connection requireConnection();

}