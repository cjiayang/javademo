package net.jyou.iodemo.charstream;

import lombok.SneakyThrows;
import net.jyou.io.ClassPathUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joey Chen
 * @created 2023/3/6 20:52
 */
public class CopyCharacters {

    @SneakyThrows
    public void copyFileCharacter(String source, String target) {
        try (
                Reader reader = new FileReader(ClassPathUtil.getFile(source));
                Writer writer = new FileWriter(ClassPathUtil.createFile(target))
        ) {
            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Reader reader = null;
        Writer writer = null;
        try {
            reader = new FileReader(ClassPathUtil.getFile("xanadu.txt"));
            writer = new FileWriter(ClassPathUtil.createFile("charoutput.txt"));
            int c;
            List<Integer> charList = new ArrayList<>();
            while ((c = reader.read()) != -1) {
                charList.add(c);
                writer.write(c);
            }
            System.out.println("charList length:" + charList.size());
            System.out.println(charList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

    }
}
