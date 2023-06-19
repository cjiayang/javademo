package net.jyou.javase.net.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author Joey Chen
 * @created 2023/6/5 19:47
 */
public class URLDemo {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");
        String protocol = url.getProtocol();
        String host = url.getHost();
        int port = url.getPort();
        String query = url.getQuery();
        String authority = url.getAuthority();

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.connect();
        InputStream in = urlConnection.getInputStream();
        String requestMethod = urlConnection.getRequestMethod();
        String responseMessage = urlConnection.getResponseMessage();
        int responseCode = urlConnection.getResponseCode();
        Object content = urlConnection.getContent();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] arr = new byte[1024];
        while (in.read(arr) > -1) {
            out.write(arr);
        }
        String str = out.toString(StandardCharsets.UTF_8);
        System.out.println(str);
        in.close();
        out.close();


//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        StringBuilder sb = new StringBuilder();
//        String tmp = "";
//        while ((tmp = reader.readLine())!= null) {
//            sb.append(tmp);
//        }
//        System.out.println(sb);
//        reader.close();
    }
}
