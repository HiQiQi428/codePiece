package org.luncert;

import java.util.HashMap;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.mbean.LoggerMBean;
import org.luncert.mbean.PropertyManagerMBean;
import org.luncert.mullog.Mullog;

@RunWith(JUnit4.class)
public class TestAgent {

    private static final String AGENT_DOMAIN = "StandardAgent";

    private JMXConnector conn;

    private MBeanServerConnection mbsc;

    @Before
    public void before() throws Exception {
        HashMap<String, Object> prop = new HashMap<String, Object>();
        prop.put(JMXConnector.CREDENTIALS, "root");
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        conn = JMXConnectorFactory.connect(url, prop);
        mbsc = conn.getMBeanServerConnection();
    }

    @After
    public void after() throws Exception {
        conn.close();
    }

    @Test
    public void testPropertyManagerMBean() throws Exception {
        ObjectName mbeanName = new ObjectName(AGENT_DOMAIN, "name", "props");
        PropertyManagerMBean mBean = JMX.newMBeanProxy(mbsc, mbeanName, PropertyManagerMBean.class);
       
        Mullog.info("> props.password =", mBean.getProperty("password"));
        mBean.setSource(TestAgent.class.getClassLoader().getResource("mullog.properties").getPath());
        Mullog.info("> props.mullog.console.type =", mBean.getProperty("mullog.console.type"));
    }

    @Test
    public void testLoggerMBean() throws Exception{
        ObjectName mbeanName = new ObjectName(AGENT_DOMAIN, "name", "logger");
        LoggerMBean mBean = JMX.newMBeanProxy(mbsc, mbeanName, LoggerMBean.class);

        Mullog.info("> logger.level =", mBean.getLogLevel());
        Mullog.info("> log in level DEBUG");
        mBean.writeLog(Mullog.MULLOG_DEBUG, "am logging in level DEBUG");
        Mullog.info("> set logger.level = ERROR");
        mBean.setLogLevel(Mullog.MULLOG_ERROR);
        Mullog.info("> log in level INFO");
        mBean.writeLog(Mullog.MULLOG_INFO, "am loggin in level INFO");
    }

}