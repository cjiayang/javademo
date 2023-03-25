package net.jyou.iodemo.charstream;

import net.jyou.io.ClassPathUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joey Chen
 * @created 2023/3/8 9:32
 */
public class CopyLines {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(ClassPathUtil.getFile("xanadu.txt")));
            writer = new PrintWriter(ClassPathUtil.createFile("lineout.txt"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                writer.println(line);
            }
            System.out.println(lines);
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
