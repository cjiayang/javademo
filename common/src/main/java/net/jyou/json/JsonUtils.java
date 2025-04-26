package net.jyou.json;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON 工具类
 *
 * @author Admin
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 忽略 null 值
    }

    /**
     * 初始化 objectMapper 属性
     * <p>
     * 通过这样的方式，使用 Spring 创建的 ObjectMapper Bean
     *
     * @param objectMapper ObjectMapper 对象
     */
    public static void init(ObjectMapper objectMapper) {
        JsonUtils.objectMapper = objectMapper;
    }

    @SneakyThrows
    public static String toJsonString(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public static byte[] toJsonByte(Object object) {
        return objectMapper.writeValueAsBytes(object);
    }

    @SneakyThrows
    public static String toJsonPrettyString(Object object) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        try {
            return objectMapper.readValue(text, clazz);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String text, String path, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        try {
            JsonNode treeNode = objectMapper.readTree(text);
            JsonNode pathNode = treeNode.path(path);
            return objectMapper.readValue(pathNode.toString(), clazz);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String text, Type type) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        try {
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructType(type));
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将字符串解析成指定类型的对象
     * 使用 {@link #parseObject(String, Class)} 时，在@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS) 的场景下，
     * 如果 text 没有 class 属性，则会报错。此时，使用这个方法，可以解决。
     *
     * @param text 字符串
     * @param clazz 类型
     * @return 对象
     */
    public static <T> T parseObject2(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        return JSONUtil.toBean(text, clazz);
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (ArrayUtil.isEmpty(bytes)) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            log.error("json parse err,json:{}", bytes, e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(text, typeReference);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析 JSON 字符串成指定类型的对象，如果解析失败，则返回 null
     *
     * @param text 字符串
     * @param typeReference 类型引用
     * @return 指定类型的对象
     */
    public static <T> T parseObjectQuietly(String text, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(text, typeReference);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseArray(String text, String path, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        try {
            JsonNode treeNode = objectMapper.readTree(text);
            JsonNode pathNode = treeNode.path(path);
            return objectMapper.readValue(pathNode.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static JsonNode parseTree(String text) {
        try {
            return objectMapper.readTree(text);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static JsonNode parseTree(byte[] text) {
        try {
            return objectMapper.readTree(text);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static boolean isJson(String text) {
        return JSONUtil.isTypeJSON(text);
    }

    public static String toJsonStr(Object o) {
        if (o == null || o instanceof String) {
            return (String) o;
        }
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * To json t.
     *
     * @param <T>     the type parameter
     * @param jsonStr the json str
     * @param t       the t
     * @return the t
     * @author Norton Lai
     * @created 2022 -03-09 17:00:09
     */
    @SneakyThrows
    public static <T> T toJson(String jsonStr, Class<T> t) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return null;
        }
        return objectMapper.readValue(jsonStr, t);
    }

    @SneakyThrows
    public static <T> T toJson(String jsonStr, JavaType t) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return null;
        }
        return objectMapper.readValue(jsonStr, t);
    }

    @SneakyThrows
    public static Map<String, Object> toJsonMap(String jsonStr) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return null;
        }
        return objectMapper.readValue(jsonStr, Map.class);
    }

    @SneakyThrows
    public static <T> List<T> toJsonList(String jsonStr, Class<T> clz) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return null;
        }
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clz);
        return objectMapper.readValue(jsonStr, type);
    }

    @SneakyThrows
    public static List<Map<String, Object>> toJsonListMap(String jsonStr) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return null;
        }
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class);
        return objectMapper.readValue(jsonStr, type);
    }


    public static JavaType getParamType(Class clz, Class... paramCls) {
        return objectMapper.getTypeFactory().constructParametricType(clz, paramCls);
    }

    public static JavaType getParamType(Class clz, JavaType... paramCls) {
        return objectMapper.getTypeFactory().constructParametricType(clz, paramCls);
    }

}
