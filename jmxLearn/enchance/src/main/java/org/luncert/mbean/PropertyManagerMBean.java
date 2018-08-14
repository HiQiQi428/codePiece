package org.luncert.mbean;

import java.util.Enumeration;

public interface PropertyManagerMBean {

    public String getProperty(String key);
    public void setProperty(String key, String value);
    public Enumeration<Object> keys();
    public void setSource(String path);
    
}