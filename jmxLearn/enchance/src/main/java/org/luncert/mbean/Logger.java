package org.luncert.mbean;

import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.luncert.mullog.Mullog;
import org.luncert.mullog.MullogManager;
import org.luncert.mullog.appender.Appender;

public class Logger implements LoggerMBean, MBeanRegistration {

	private Appender consoleAppender;
	
	private MBeanServer server;

    public Logger() {
		consoleAppender = MullogManager.getAppender("console");
	}
	
	private void loadLogLevel(String logLevel) {
		for (int i = 0, limit = Mullog.MULLOG_DEBUG + 1; i < limit; i++)
			if (logLevel.toUpperCase().compareTo(Mullog.MULLOG_LEVEL[i]) == 0) {
				consoleAppender.setLogLevel(i);
				break;
			}
	}

	@Override
	public void setLogLevel(int level) { consoleAppender.setLogLevel(level); }

	@Override
	public int getLogLevel() { return consoleAppender.getLogLevel(); }

	@Override
	public void writeLog(int logLevel, String message) { Mullog.log(logLevel, message); }

	@Override
	public ObjectName preRegister(MBeanServer server, ObjectName name) throws Exception {
		this.server = server;
		ObjectName name1 = new ObjectName("StandardAgent", "name", "props");
		Object[] params = { "logLevel" };
		String[] signature = { "java.lang.String" };
		String value = (String)server.invoke(name1, "getProperty", params, signature);
		loadLogLevel(value);
		return name;
	}

	@Override
	public void postRegister(Boolean registrationDone) {}

	@Override
	public void preDeregister() throws Exception {}

	@Override
	public void postDeregister() {}

}