/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.jyou.io;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;

/**
 * @author Joey Chen
 * @created 2023/3/2 22:25
 */
public class ClassPathUtil {
    public static File getFile(@NonNull String fileName) {
        URL url = ClassPathUtil.class.getResource("/" + fileName);
        assert url != null;
        return new File(url.getFile());
    }

    @SneakyThrows
    public static File createFile(@NonNull String fileName) {
        File file = new File(getRootPath() +  fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static String getRootPath() {
        URL base = ClassPathUtil.class.getResource("/");
        assert base != null;
        return base.getPath();
    }
}
