package org.luncert;

import java.io.IOException;
import java.util.HashMap;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.mbean.HelloMBean;

@RunWith(JUnit4.class)
public class TestRMIClient {

    @Test
    public void test() throws IOException, MalformedObjectNameException {
        HashMap<String, Object> prop = new HashMap<String, Object>();
        prop.put(JMXConnector.CREDENTIALS, "Hello");
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        JMXConnector conn = JMXConnectorFactory.connect(url, prop);

        MBeanServerConnection mbsc = conn.getMBeanServerConnection();

        ObjectName mbeanName = new ObjectName("DefaultDomain:name=Hello");
        HelloMBean hello = JMX.newMBeanProxy(mbsc, mbeanName, HelloMBean.class);
        hello.setName("Luncert");
        hello.sayHello();
    }

}