package net.jyou.javase.jmx;

import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class JmxServer {

    private Registry registry;
    private JMXConnectorServer jmxConnectorServer;
    private int port = 1099;

    public JmxServer() {}

    public JmxServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        if (registry == null && jmxConnectorServer == null) {
            registry = LocateRegistry.createRegistry(port);
            String serverURL = "service:jmx:rmi://localhost:" + port + "/jndi/rmi://localhost:" + port+ "/jmxrmi";
            JMXServiceURL jmxServiceURL = new JMXServiceURL(serverURL);
            jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, ManagementFactory.getPlatformMBeanServer());
            jmxConnectorServer.start();
        }
    }

    public void stop() throws IOException {
        if (jmxConnectorServer != null && registry != null) {
            jmxConnectorServer.stop();
            UnicastRemoteObject.unexportObject((Remote) jmxConnectorServer, true);
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        JmxServer jmxServer = new JmxServer();
        System.out.println("启动JMXServer");
        jmxServer.start();
        System.out.println("启动成功");
        Thread.sleep(6000000);
    }
}
