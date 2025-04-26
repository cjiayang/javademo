package net.jyou.javase.jmx;

import javax.management.Attribute;
import javax.management.MBeanAttributeInfo;
import javax.management.ObjectName;
import java.net.Inet4Address;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        Inet4Address inet4Address = Inet4Address.ofLiteral("172.16.16.47");
        JmxClient client = new JmxClient(inet4Address, 1616);



        String[] beanDomains = client.getBeanDomains();
        for (String beanDomain : beanDomains) {
            System.out.println("===================");
            System.out.println("beanDomain: " + beanDomain);

            Set<ObjectName> beanNames = client.getBeanNames(beanDomain);
            for (ObjectName beanName : beanNames) {
                System.out.println(">>>>>>>>");
                System.out.println("beanName: " + beanName);
                MBeanAttributeInfo[] attributesInfo = client.getAttributesInfo(beanName);
                String[] attrs = Arrays.stream(attributesInfo).map(MBeanAttributeInfo::getName)
                        .toArray(String[]::new);
                List<Attribute> attributes = client.getAttributes(beanName, attrs);
                for (Attribute attribute : attributes) {
                    System.out.println(attribute.getName() + ": " + attribute.getValue());
                }
            }
        }
        client.close();
    }

}
