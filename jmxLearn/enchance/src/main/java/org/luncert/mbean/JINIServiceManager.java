package org.luncert.mbean;

import java.lang.reflect.Method;
import java.util.Map.Entry;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;

import org.luncert.ExceptionUtil;

public class JINIServiceManager implements DynamicMBean {

    private ManagedJINIService serviceRef;

    private String jiniInterfaceName;

    private Entry initialAttribute;

    private String serviceName;

    /*
    private Object lookUpService() {
        try {
            Class[] interfaces = { Class.forName( jiniInterfaceName ) };
            Entry[] ents = new Entry[ 1 ];
            ents[ 0 ] = initialAttribute;
            ServiceTemplate template = new ServiceTemplate(null,interfaces,ents );
            ServiceRegistrar reg = RegistryFinder.getRegistry();
            ServiceMatches matches = reg.lookup( template,10000 );
            ServiceItem item = matches.items[ 0 ];
            return item.service;
        } catch( Exception e ) { ExceptionUtil.handle(e); }
        return null;
    }
    */

    public JINIServiceManager(Entry att) {
        jiniInterfaceName = "org.luncert.mbean.ManagedJINIService";
        // serviceRef = (ManagedJINIService)lookUpService();
        initialAttribute = att;
    }

	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException, ReflectionException {
        throw new AttributeNotFoundException(attribute);
	}

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        throw new AttributeNotFoundException("No attributes can be set");
	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		return new AttributeList();
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		return null;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
        try {
            String methodName = actionName;
            Class<?>[] types = new Class[signature.length];
            for (int i = 0; i < types.length; i++) types[i] = Class.forName(signature[i]);
            Method m = serviceRef.getClass().getMethod(methodName, types);
            Object temp = m.invoke(serviceRef, params);
            return temp;
        } catch (Exception e) { throw new MBeanException(e); }
	}

	@Override
	public MBeanInfo getMBeanInfo() {
        MBeanConstructorInfo[] cons = new MBeanConstructorInfo[1];
        MBeanNotificationInfo[] nots = null;
        MBeanAttributeInfo[] atts = null;
        MBeanOperationInfo[] ops = new MBeanOperationInfo[2];
        try {
            Class[] conargs = {String.class};
        } catch (Exception e) {}
		return null;
	}

}