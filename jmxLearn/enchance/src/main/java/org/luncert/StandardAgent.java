package org.luncert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.security.auth.Subject;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import org.luncert.mbean.Logger;
import org.luncert.mbean.LoggerMBean;
import org.luncert.mbean.PropertyManager;
import org.luncert.mbean.PropertyManagerMBean;
import org.luncert.mullog.Mullog;

public class StandardAgent {

    private MBeanServer server;

    public StandardAgent() throws Exception {
        server = MBeanServerFactory.createMBeanServer("StandardAgent");
        // regis MBean
        ObjectName name = new ObjectName("StandardAgent", "name", "props");
        PropertyManagerMBean propertyManagerMBean = new PropertyManager(StandardAgent.class.getClassLoader().getResource("test.properties").getPath());
        server.registerMBean(propertyManagerMBean, name);      
        Mullog.info("> PropertyManagerMBean registed");

        name = new ObjectName("StandardAgent", "name", "logger");
        LoggerMBean loggerMBean = new Logger();
        server.registerMBean(loggerMBean, name);
        Mullog.info("> LoggerMBean registed");
        // startHTMLAdapter();
        startRMIConnector();
    }

    protected void startHTMLAdapter() {
        HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
        try {
            ObjectName name = new ObjectName("JMXBookAgent:name=HTMLAdaptor");
            server.registerMBean(adaptor, name);
            adaptor.setPort(9092);
            adaptor.start();
        } catch (Exception e) { ExceptionUtil.handle(e); }
    }

    protected void startRMIConnector() throws MalformedURLException, IOException, InterruptedException {
        LocateRegistry.createRegistry(1099);
        Mullog.info("> registry listening on 1099");
        
        HashMap<String, Object> prop = new HashMap<String, Object>();
        prop.put(JMXConnectorServer.AUTHENTICATOR, new JMXAuthenticator() {
            public Subject authenticate(Object credentials) {
                if (credentials instanceof String && credentials.equals("root"))
                    return new Subject();
                else throw new SecurityException("not authicated");
            }
        });
        JMXConnectorServer cserver = JMXConnectorServerFactory.newJMXConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi"), prop, server);
        cserver.start();
        
        Mullog.info("> rmi server started");
    }

    public static void main(String[] args) throws Exception {
        new StandardAgent();
    }
}