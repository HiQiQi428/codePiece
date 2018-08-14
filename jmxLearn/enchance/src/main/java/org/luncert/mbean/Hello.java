package org.luncert.mbean;

import org.luncert.mullog.Mullog;

public class Hello implements HelloMBean {

    private static final long serialVersionUID = 293867618665551880L;
    
    private String name;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void sayHello() {
		Mullog.info("Hello,", name);
	}

}