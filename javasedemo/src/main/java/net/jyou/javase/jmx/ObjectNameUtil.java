package net.jyou.javase.jmx;



import javax.management.ObjectName;

/**
 * Utility class that creates {@link ObjectName} objects from various arguments.
 *
 * @author graywatson
 */
public class ObjectNameUtil {

    private ObjectNameUtil() {
        // only for static methods
    }

    /**
     * Constructs an object-name from a domain-name and object-name.
     *
     * @param domainName
     *            This corresponds to the {@link JmxResource#domainName()} and is the top level folder name for the
     *            beans.
     * @param beanName
     *            This corresponds to the {@link JmxResource#beanName()} and is the bean name in the lowest folder
     *            level.
     * @throws IllegalArgumentException
     *             If we had problems building the name
     */
    public static ObjectName makeObjectName(String domainName, String beanName) {
        // j256:00=clients,name=Foo
        StringBuilder sb = new StringBuilder();
        sb.append(domainName);
        sb.append(':');
        boolean first = true;
        int prefixC = 0;

        if (!first) {
            sb.append(',');
        }
        sb.append("name=");
        sb.append(beanName);
        String objectNameString = sb.toString();
        return makeObjectName(objectNameString);
    }

    /**
     * Constructs an object-name from a string suitable to be passed to the {@link ObjectName} constructor.
     *
     * @param objectNameString
     *            The entire object-name string in the form of something like
     *            <tt>your.domain:folder=1,name=beanBean</tt>
     * @throws IllegalArgumentException
     *             If we had problems building the name
     */
    public static ObjectName makeObjectName(String objectNameString) {
        try {
            return new ObjectName(objectNameString);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ObjectName generated: " + objectNameString, e);
        }
    }
}
