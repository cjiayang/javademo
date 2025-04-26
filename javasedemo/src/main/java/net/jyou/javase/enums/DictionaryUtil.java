package net.jyou.javase.enums;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DictionaryUtil {
    public static <D extends IDictionary> List<Map<String, Object>> getDictionaryList(Class<D> clazz) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        Arrays.stream(clazz.getEnumConstants()).forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("key", item.getKey());
            map.put("lable", item.getLabel());
            mapList.add(map);
        });
        return mapList;
    }

    public static <D extends IDictionary> List<Map<String, Object>> getDictionaryList(Class<D> clazz, String... props) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Arrays.stream(clazz.getEnumConstants()).forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            for (String prop : props) {
                try {
                    Method method = clazz.getMethod("get" + StrUtil.upperFirst(prop));
                    Object invoke = method.invoke(item);
                    map.put(prop, invoke);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            mapList.add(map);
        });
        return mapList;
    }

    public static <D extends IDictionary> List<Map<String, Object>> getDictionaryList(Class<D> clazz, IFunction<String, D> lambdas) {
        return null;
    }
}
