package org.luncert.mbean;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.luncert.ExceptionUtil;

public class PropertyManager implements PropertyManagerMBean {

	private Properties props = null;
	
	private void loadFromFile(String path) {
		// load supplied property file
        try {
			props = new Properties();
			FileInputStream fin = new FileInputStream(path);
			props.load(fin);
			fin.close();
        } catch (Exception e) { ExceptionUtil.handle(e); }
	}

    public PropertyManager(String path) { loadFromFile(path); }

	@Override
	public String getProperty(String key) { return props.getProperty(key); }

	@Override
	public void setProperty(String key, String value) { props.setProperty(key, value); }

	@Override
	public Enumeration<Object> keys() { return props.keys(); }

	@Override
	public void setSource(String path) { loadFromFile(path); }

}