package net.jyou.iodemo.bytestream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.jyou.io.ClassPathUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joey Chen
 * @created 2023/3/2 22:19
 */
@Slf4j
public class CopyBytes {

    /**
     * 每次读取1字节，读取后的返回值是该字节的数值表示
     * @param source 输入文件
     * @param target 输出文件
     */
    @SneakyThrows
    public void copyFileByte(String source, String target) {
        try (
                InputStream in = new FileInputStream(ClassPathUtil.getFile(source));
                OutputStream out = new FileOutputStream(ClassPathUtil.createFile(target))
        ) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = in.read())!= -1){
                out.write(c);
                sb.append(c).append(" ");
            }
            System.out.println(sb);
        }
    }

    /**
     * 每次读取n字节到byte缓冲区，read（）返回值代表读取的字节数
     * @param source 输入文件
     * @param target 输出文件
     */
    @SneakyThrows
    public void copyFileBytes(String source, String target) {
        try (
                InputStream in = new FileInputStream(ClassPathUtil.getFile(source));
                OutputStream out = new FileOutputStream(ClassPathUtil.createFile(target))
        ) {
            StringBuilder sb = new StringBuilder();
            byte[] bytes = new byte[3];
            int len;
            while ((len = in.read(bytes))!= -1){
                out.write(bytes);
                sb.append(len).append(" ");
            }
            System.out.println(sb);
        }
    }

    /**
     * 缓冲流内部内置了一个缓冲区数组，底层流一次性读取后放入缓冲数组里，默认8192。
     * read()方法每次读取其实是从缓冲数组里面读。缓冲数组里读完了，底层就继续读往缓冲区里读
     * @param source 输入文件
     * @param target 输出文件
     */
    @SneakyThrows
    public void copyFileByteBuffer(String source, String target) {
        try (
                InputStream in = new BufferedInputStream(new FileInputStream(ClassPathUtil.getFile(source)));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(ClassPathUtil.createFile(target)))
        ) {
            StringBuilder sb = new StringBuilder();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
                sb.append(b).append(" ");
            }
            System.out.println(sb);
        }
    }



    @SneakyThrows
    public static void main(String[] args) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(ClassPathUtil.getFile("xanadu.txt"));
            outputStream = new FileOutputStream(ClassPathUtil.createFile("outagain.txt"));
            List<Integer> byteList = new ArrayList<>();
            int c;
            while ((c = inputStream.read()) != -1) {
                byteList.add(c);
                outputStream.write(c);
            }
            System.out.println("byteList length:" + byteList.size());
            System.out.println(byteList);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
