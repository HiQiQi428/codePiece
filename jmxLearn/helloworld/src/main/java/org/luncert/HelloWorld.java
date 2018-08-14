package org.luncert;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class HelloWorld extends NotificationBroadcasterSupport implements HelloWorldMBean {

    private String greeting = null;

    public HelloWorld() {
		this.greeting = "Hello World! I am a Standard MBean";
    }

	@Override
	public void setGreeting(String greeting) {
		this.greeting = greeting;
		sendNotification(new Notification("jmxbook.ch2.helloWorld.test", this, -1, System.currentTimeMillis(), greeting));
	}

	@Override
	public String getGreeting() {
		return greeting;
	}

	@Override
	public String printGreeting(String param) {
		System.out.println(param);
		return "echo >> " + param;
	}

}