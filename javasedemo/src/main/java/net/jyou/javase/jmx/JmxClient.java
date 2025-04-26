package net.jyou.javase.jmx;

import cn.hutool.core.io.IoUtil;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


/**
 *
 * 代码来源：https://github.com/j256/simplejmx/blob/master/src/main/java/com/j256/simplejmx/client/JmxClient.java
 *
 * JMX client connection implementation which connects to a JMX server and gets JMX information, gets/sets attributes,
 * and invokes operations.
 *
 * @author graywatson
 */
public class JmxClient implements Closeable {

    private JMXConnector jmxConnector;
    private JMXServiceURL serviceUrl;
    private MBeanServerConnection mbeanConn;

    private final static Map<String, String> primitiveObjectMap = new HashMap<String, String>();

    static {
        primitiveObjectMap.put(boolean.class.getName(), Boolean.class.getName());
        primitiveObjectMap.put(byte.class.getName(), Byte.class.getName());
        primitiveObjectMap.put(char.class.getName(), Character.class.getName());
        primitiveObjectMap.put(short.class.getName(), Short.class.getName());
        primitiveObjectMap.put(int.class.getName(), Integer.class.getName());
        primitiveObjectMap.put(long.class.getName(), Long.class.getName());
        primitiveObjectMap.put(float.class.getName(), Float.class.getName());
        primitiveObjectMap.put(double.class.getName(), Double.class.getName());
        // NOTE: don't need void/Void
    }

    /**
     * <p>
     * Connect the client to a JMX server using the full JMX URL format. The URL should look something like:
     * </p>
     *
     * <pre>
     * service:jmx:rmi:///jndi/rmi://hostName:portNumber/jmxrmi
     * </pre>
     */
    public JmxClient(String jmxUrl) throws JMException {
        this(jmxUrl, null);
    }

    /**
     * <p>
     * Connect the client to a JMX server using the full JMX URL format with username/password credentials. The URL
     * should look something like:
     * </p>
     *
     * <pre>
     * service:jmx:rmi:///jndi/rmi://hostName:portNumber/jmxrmi
     * </pre>
     */
    public JmxClient(String jmxUrl, String userName, String password) throws JMException {
        this(jmxUrl, addCredentialsToMap(userName, password, null));
    }

    /**
     * <p>
     * Connect the client to a JMX server using the full JMX URL format with username/password credentials. The URL
     * should look something like:
     * </p>
     *
     * <pre>
     * service:jmx:rmi:///jndi/rmi://hostName:portNumber/jmxrmi
     * </pre>
     */
    public JmxClient(String jmxUrl, String userName, String password, Map<String, Object> environmentMap)
            throws JMException {
        this(jmxUrl, addCredentialsToMap(userName, password, environmentMap));
    }

    /**
     * Connect the client to the local host at a certain port number.
     */
    public JmxClient(int localPort) throws JMException {
        this(generalJmxUrlForHostNamePort("", localPort), null);
    }

    /**
     * Connect the client to a host and port combination.
     */
    public JmxClient(String hostName, int port) throws JMException {
        this(generalJmxUrlForHostNamePort(hostName, port), null);
    }

    /**
     * Connect the client to a host and port combination and a username and password.
     */
    public JmxClient(String hostName, int port, String userName, String password) throws JMException {
        this(generalJmxUrlForHostNamePort(hostName, port), addCredentialsToMap(userName, password, null));
    }

    /**
     * Connect the client to a host and port combination.
     */
    public JmxClient(String hostName, int port, Map<String, Object> environment) throws JMException {
        this(generalJmxUrlForHostNamePort(hostName, port), environment);
    }

    /**
     * Connect the client to an address and port combination.
     */
    public JmxClient(InetAddress address, int port) throws JMException {
        this(generalJmxUrlForHostNamePort(address.getHostAddress(), port), null);
    }

    /**
     * <p>
     * Connect the client to a JMX server using the full JMX URL format an environment-map passed into
     * {@link JMXConnectorFactory#connect(JMXServiceURL, Map)}. The URL should look something like:
     * </p>
     *
     * <pre>
     * service:jmx:rmi:///jndi/rmi://hostName:portNumber/jmxrmi
     * </pre>
     */
    public JmxClient(String jmxUrl, Map<String, Object> environmentMap) throws JMException {

        if (jmxUrl == null) {
            throw new IllegalArgumentException("Jmx URL cannot be null");
        }

        try {
            this.serviceUrl = new JMXServiceURL(jmxUrl);
        } catch (MalformedURLException e) {
            throw createJmException("JmxServiceUrl was malformed: " + jmxUrl, e);
        }

        try {
            jmxConnector = JMXConnectorFactory.connect(serviceUrl, environmentMap);
            mbeanConn = jmxConnector.getMBeanServerConnection();
        } catch (IOException e) {
            if (jmxConnector != null) {
                IoUtil.close(jmxConnector);
                jmxConnector = null;
            }
            throw createJmException("Problems connecting to the server" + e, e);
        }
    }

    /**
     * Returns a JMX/RMI URL for a host-name and port.
     */
    public static String generalJmxUrlForHostNamePort(String hostName, int port) {
        return "service:jmx:rmi:///jndi/rmi://" + hostName + ":" + port + "/jmxrmi";
    }

    /**
     * Close the client connection to the mbean server.If you want a method that throws then use {@link #closeThrow()}.
     */
    @Override
    public void close() {
        try {
            closeThrow();
        } catch (JMException e) {
            // ignored
        }
    }

    /**
     * Close the client connection to the mbean server. If you want a method that does not throw then use
     * {@link #close()}.
     */
    public void closeThrow() throws JMException {
        try {
            if (jmxConnector != null) {
                jmxConnector.close();
                jmxConnector = null;
            }
            // NOTE: doesn't seem to be close method on MBeanServerConnection
            mbeanConn = null;
        } catch (IOException e) {
            throw createJmException("Could not close the jmx connector", e);
        }
    }

    /**
     * Return an array of the bean's domain names.
     */
    public String[] getBeanDomains() throws JMException {
        checkClientConnected();
        try {
            return mbeanConn.getDomains();
        } catch (IOException e) {
            throw createJmException("Problems getting jmx domains: " + e, e);
        }
    }

    /**
     * Return a set of the various bean ObjectName objects associated with the Jmx server.
     */
    public Set<ObjectName> getBeanNames() throws JMException {
        checkClientConnected();
        try {
            return mbeanConn.queryNames(null, null);
        } catch (IOException e) {
            throw createJmException("Problems querying for jmx bean names: " + e, e);
        }
    }

    /**
     * Return a set of the various bean ObjectName objects associated with the Jmx server.
     */
    public Set<ObjectName> getBeanNames(String domain) throws JMException {
        checkClientConnected();
        try {
            return mbeanConn.queryNames(ObjectName.getInstance(domain + ":*"), null);
        } catch (IOException e) {
            throw createJmException("Problems querying for jmx bean names: " + e, e);
        }
    }

    /**
     * Return an array of the attributes associated with the bean name.
     */
    public MBeanAttributeInfo[] getAttributesInfo(String domainName, String beanName) throws JMException {
        return getAttributesInfo(ObjectNameUtil.makeObjectName(domainName, beanName));
    }

    /**
     * Return an array of the attributes associated with the bean name.
     */
    public MBeanAttributeInfo[] getAttributesInfo(ObjectName name) throws JMException {
        checkClientConnected();
        try {
            return mbeanConn.getMBeanInfo(name).getAttributes();
        } catch (Exception e) {
            throw createJmException("Problems getting bean information from " + name, e);
        }
    }

    /**
     * Return information for a particular attribute name.
     */
    public MBeanAttributeInfo getAttributeInfo(ObjectName name, String attrName) throws JMException {
        checkClientConnected();
        return getAttrInfo(name, attrName);
    }

    /**
     * Return the value of a JMX attribute.
     */
    public Object getAttribute(String domain, String beanName, String attributeName) throws Exception {
        return getAttribute(ObjectNameUtil.makeObjectName(domain, beanName), attributeName);
    }

    /**
     * Return the value of a JMX attribute.
     */
    public Object getAttribute(ObjectName name, String attributeName) throws Exception {
        checkClientConnected();
        return mbeanConn.getAttribute(name, attributeName);
    }

    /**
     * Return the value of a JMX attribute as a String.
     */
    public String getAttributeString(String domain, String beanName, String attributeName) throws Exception {
        return getAttributeString(ObjectNameUtil.makeObjectName(domain, beanName), attributeName);
    }

    /**
     * Return the value of a JMX attribute as a String or null if attribute has a null value.
     */
    public String getAttributeString(ObjectName name, String attributeName) throws Exception {
        Object bean = getAttribute(name, attributeName);
        if (bean == null) {
            return null;
        } else {
            return ClientUtils.valueToString(bean);
        }
    }

    /**
     * Get multiple attributes at once from the server.
     */
    public List<Attribute> getAttributes(ObjectName name, String[] attributes) throws Exception {
        checkClientConnected();
        return mbeanConn.getAttributes(name, attributes).asList();
    }

    /**
     * Get multiple attributes at once from the server.
     */
    public List<Attribute> getAttributes(String domain, String beanName, String[] attributes) throws Exception {
        checkClientConnected();
        return getAttributes(ObjectNameUtil.makeObjectName(domain, beanName), attributes);
    }



    private static Map<String, Object> addCredentialsToMap(String userName, String password,
                                                           Map<String, Object> environmentMap) {
        if (environmentMap == null) {
            environmentMap = new HashMap<String, Object>();
        }
        if (userName != null || password != null) {
            String[] credentials = new String[] { userName, password };
            environmentMap.put(JMXConnector.CREDENTIALS, credentials);
        }
        return environmentMap;
    }


    private String[] lookupParamTypes(ObjectName objectName, String operName, Object[] params) throws JMException {
        checkClientConnected();
        MBeanOperationInfo[] operations;
        try {
            operations = mbeanConn.getMBeanInfo(objectName).getOperations();
        } catch (Exception e) {
            throw createJmException("Cannot get attribute info from " + objectName, e);
        }
        String[] paramTypes = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass().getName();
        }
        int nameC = 0;
        String[] first = null;
        for (MBeanOperationInfo info : operations) {
            if (!info.getName().equals(operName)) {
                continue;
            }
            MBeanParameterInfo[] mbeanParams = info.getSignature();
            if (params.length != mbeanParams.length) {
                continue;
            }
            String[] signatureTypes = new String[mbeanParams.length];
            for (int i = 0; i < params.length; i++) {
                signatureTypes[i] = mbeanParams[i].getType();
            }
            if (paramTypes.length == signatureTypes.length) {
                boolean found = true;
                for (int i = 0; i < paramTypes.length; i++) {
                    if (!isClassNameEquivalent(paramTypes[i], signatureTypes[i])) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return signatureTypes;
                }
            }
            first = signatureTypes;
            nameC++;
        }

        if (first == null) {
            throw new IllegalArgumentException("Cannot find operation named '" + operName + "'");
        } else if (nameC > 1) {
            throw new IllegalArgumentException(
                    "Cannot find operation named '" + operName + "' with matching argument types");
        } else {
            // return the first one we found that matches the name
            return first;
        }
    }

    private boolean isClassNameEquivalent(String className1, String className2) {
        return getWrapperClass(className1).equals(getWrapperClass(className2));
    }

    private String getWrapperClass(String className) {
        String wrapperClassName = primitiveObjectMap.get(className);
        if (wrapperClassName == null) {
            return className;
        } else {
            return wrapperClassName;
        }
    }

    private void checkClientConnected() {
        if (mbeanConn == null) {
            throw new IllegalArgumentException("JmxClient is not connected");
        }
    }

    private MBeanAttributeInfo getAttrInfo(ObjectName objectName, String attrName) throws JMException {
        MBeanAttributeInfo[] attributes;
        try {
            attributes = mbeanConn.getMBeanInfo(objectName).getAttributes();
        } catch (Exception e) {
            throw createJmException("Cannot get attribute info from " + objectName, e);
        }
        for (MBeanAttributeInfo info : attributes) {
            if (info.getName().equals(attrName)) {
                return info;
            }
        }
        return null;
    }

    private JMException createJmException(String message, Exception e) {
        JMException jmException = new JMException(message);
        jmException.initCause(e);
        return jmException;
    }

}
