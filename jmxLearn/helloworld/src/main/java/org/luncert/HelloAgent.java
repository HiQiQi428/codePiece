package org.luncert;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent implements NotificationListener {
    
    private MBeanServer mbs;

    public HelloAgent() {
        mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
        HelloWorld hw = new HelloWorld();
        HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
        
        try {
            ObjectName adapterName = new ObjectName("HelloAgent:name=htmladaptor,port=9092");
            mbs.registerMBean(adaptor, adapterName);
            adaptor.setPort(9092);
            adaptor.start();

            ObjectName helloWorldName = new ObjectName("HelloAgent", "name", "helloWorld1");
            mbs.registerMBean(hw, helloWorldName);
            hw.addNotificationListener(this, null, null);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String args[]) {
        System.out.println("HelloAgent is running ...");
        new HelloAgent();
    }

	@Override
	public void handleNotification(Notification notification, Object handback) {
        System.out.println("Receiving notification...");
        System.out.println(notification.getType());
        System.out.println(notification.getMessage());
	}
}