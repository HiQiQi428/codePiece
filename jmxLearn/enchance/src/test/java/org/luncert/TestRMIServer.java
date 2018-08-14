package org.luncert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.security.auth.Subject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.mbean.Hello;
import org.luncert.mbean.HelloMBean;
import org.luncert.mullog.Mullog;

@RunWith(JUnit4.class)
public class TestRMIServer {

    @Test
    public void test() throws MalformedURLException, IOException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException, InterruptedException {
        Mullog.info("> starting JMX Agent");

        LocateRegistry.createRegistry(1099);
        Mullog.info("> registry listening on 1099");
        
        MBeanServer server = MBeanServerFactory.createMBeanServer();

        HashMap<String, Object> prop = new HashMap<String, Object>();
        prop.put(JMXConnectorServer.AUTHENTICATOR, new JMXAuthenticator() {
            public Subject authenticate(Object credentials) {
                if (credentials instanceof String && credentials.equals("Hello"))
                    return new Subject();
                else
                    throw new SecurityException("not authicated");
            }
        });
        JMXConnectorServer cserver = JMXConnectorServerFactory.newJMXConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi"), prop, server);  
        cserver.start();
        Mullog.info("> rmi server started");

        ObjectName helloName = new ObjectName(server.getDefaultDomain(), "name", "Hello");
        HelloMBean hello = new Hello();
        server.registerMBean(hello, helloName);
        Mullog.info("> HelloMBean registed");

        // for (ObjectInstance object : server.queryMBeans(null, null)) Mullog.info("\t", object.getObjectName());
        // Mullog.info(hello);
        Mullog.info("> JMX Agent started");
        Mullog.info("> JMX Agent will shutdown in 10s");
        Thread.sleep(10000);
    }

}