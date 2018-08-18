package org.luncert;

import java.nio.file.Paths;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;
import org.apache.coyote.http11.Http11NioProtocol;

public class EmbedTomcatHttps {

    public static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";
    static final String docBase = Paths.get(System.getProperty("user.dir"), "tomcat-tmp").toString();
    static final int httpPort = 9000;
    static final int httpsPort = 9090;

    public EmbedTomcatHttps() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(httpPort);
        tomcat.setBaseDir(docBase);

        Connector connector = new Connector(DEFAULT_PROTOCOL);
        connector.setPort(httpsPort);

        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        protocol.setKeystorePass("123456");
        protocol.setKeystoreFile("myKey");
        protocol.setSSLEnabled(true);

        tomcat.getService().addConnector(connector);
        tomcat.getHost().setAutoDeploy(false);

        String contextPath = "/book";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new FixContextListener());
        tomcat.getHost().addChild(context);

        tomcat.addServlet(contextPath, "homeServlet", new HomeServlet());
        context.addServletMappingDecoded("/home", "homeServlet");
        tomcat.start();
        tomcat.getServer().await();
    }
    
}