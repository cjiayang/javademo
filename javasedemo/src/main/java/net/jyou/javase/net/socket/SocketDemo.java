package net.jyou.javase.net.socket;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Joey Chen
 * @created 2023/6/10 11:19
 */
public class SocketDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        SocketAddress address = new InetSocketAddress("www.baidu.com", 80);
        socket.connect(address);
        InputStream inputStream = socket.getInputStream();
        int port = socket.getPort();
        InetAddress inetAddress = socket.getInetAddress();
        boolean keepAlive = socket.getKeepAlive();
        int soTimeout = socket.getSoTimeout();
        int soLinger = socket.getSoLinger();
        boolean reuseAddress = socket.getReuseAddress();
        boolean tcpNoDelay = socket.getTcpNoDelay();
        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
        int receiveBufferSize = socket.getReceiveBufferSize();
        int sendBufferSize = socket.getSendBufferSize();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        StringBuilder content = new StringBuilder();
        while (!(line = reader.readLine()).isEmpty()) {
            content.append(line);
        }
        System.out.println(content);
        socket.close();
    }
}
