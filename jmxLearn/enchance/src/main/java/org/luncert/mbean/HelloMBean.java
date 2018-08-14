package org.luncert.mbean;

import java.io.Serializable;

public interface HelloMBean extends Serializable {

    public void setName(String name);

    public String getName();

    public void sayHello();

}