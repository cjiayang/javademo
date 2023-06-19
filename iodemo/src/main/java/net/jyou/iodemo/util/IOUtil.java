package net.jyou.iodemo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Joey Chen
 * @created 2023/3/27 12:02
 */
public class IOUtil {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\admin\\Desktop\\demo.json");

        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.lines()
                    .forEach(item->{
                        int index = item.indexOf("//");
                        if (index > 0) {
                            String substring = item.substring(0, index - 1);
                            builder.append(substring);
                        } else {
                            builder.append(item);
                        }

                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(builder);
    }
}
