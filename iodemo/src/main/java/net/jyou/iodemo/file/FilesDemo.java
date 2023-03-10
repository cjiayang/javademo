/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.jyou.iodemo.file;

import lombok.SneakyThrows;
import net.jyou.io.ClassPathUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Joey Chen
 * @created 2023/3/8 23:44
 */
public class FilesDemo {
    @SneakyThrows
    public static void main(String[] args) {
        Path path = Paths.get(ClassPathUtil.getFile("mylife.txt").getPath());
        boolean exists = Files.exists(path);
        Path file = null;
        if (!exists) {
            file = Files.createFile(path);
        }
        System.out.println(path == file);
    }
}
