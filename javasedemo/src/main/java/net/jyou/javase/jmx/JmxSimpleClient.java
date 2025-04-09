package net.jyou.javase.jmx;

import lombok.SneakyThrows;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ThreadInfo;

public class JmxSimpleClient {


    @SneakyThrows
    public static void main(String[] args)  {
        String domainName = "MyMBean";
        String host = "172.16.16.47";
        int rmiPort = 1616;
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + host + ":" + rmiPort + "/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        //print domains
        System.out.println("Domains:------------------");
        String domains[] = mbsc.getDomains();
        for(int i=0;i<domains.length;i++){
            System.out.println("\tDomain["+i+"] = "+domains[i]);
        }
        //MBean count
        System.out.println("MBean count = "+mbsc.getMBeanCount());

        //process attribute
        /*ObjectName mBeanName = new ObjectName(domainName+":name=HelloWorld");
        mbsc.setAttribute(mBeanName, new Attribute("Name","zzh"));//注意这里是Name而不是name
        System.out.println("Name = "+mbsc.getAttribute(mBeanName, "Name"));

        //接下去是执行Hello中的printHello方法，分别通过代理和rmi的方式执行
        //via rmi
        mbsc.invoke(mBeanName, "printHello", null, null);
        mbsc.invoke(mBeanName, "printHello", new String[]{"jizhi gril"}, new String[]{String.class.getName()});

        //get mbean information
        MBeanInfo info = mbsc.getMBeanInfo(mBeanName);
        System.out.println("Hello Class: "+info.getClassName());
        for(int i=0;i<info.getAttributes().length;i++){
            System.out.println("Hello Attribute:"+info.getAttributes()[i].getName());
        }
        for(int i=0;i<info.getOperations().length;i++){
            System.out.println("Hello Operation:"+info.getOperations()[i].getName());
        }

        //ObjectName of MBean
        System.out.println("all ObjectName:--------------");
        Set<ObjectInstance> set = mbsc.queryMBeans(null, null);
        for(Iterator<ObjectInstance> it = set.iterator(); it.hasNext();){
            ObjectInstance oi = it.next();
            System.out.println("\t"+oi.getObjectName());
        }*/
        jmxc.close();
    }


    public static void getThreadsStacks(MBeanServerConnection beanConn) throws MalformedObjectNameException, ReflectionException, InstanceNotFoundException, MBeanException, IOException {
        StringBuilder sb = new StringBuilder();
        CompositeData[] datas= (CompositeData[]) beanConn.invoke(
                new ObjectName("java.lang:type=Threading"),
                "dumpAllThreads",
                new Object[]{false, false}, new String[]{boolean.class.getName(), boolean.class.getName()});
        for (CompositeData data : datas) {
            ThreadInfo info = ThreadInfo.from(data);
            sb.append(info);

        }
        System.out.println(sb);
    }

}
