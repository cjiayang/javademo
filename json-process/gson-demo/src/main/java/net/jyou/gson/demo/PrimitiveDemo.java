package net.jyou.gson.demo;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Joey Chen
 * @created 2023/7/30 9:24
 */
@Slf4j
public class PrimitiveDemo {
    public static void main(String[] args) {
        // Serialization
        Gson gson = new Gson();
        log.info("----------Serialization----------");
        log.debug("int   : {}", gson.toJson(1));            // ==> 1
        log.debug("String: {}", gson.toJson("abcd"));       // ==> "abcd"
        log.debug("long  : {}", gson.toJson(10L)); // ==> 10
        int[] values = { 1 };
        log.debug("Array : {}", gson.toJson(values));       // ==> [1]

        // Deserialization
        int i = gson.fromJson("1", int.class);
        Integer intObj = gson.fromJson("1", Integer.class);
        Long longObj = gson.fromJson("1", Long.class);
        Boolean boolObj = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] strArray = gson.fromJson("[\"abc\"]", String[].class);
        log.info("----------Deserialization----------");
        log.debug("int    : {}", i);
        log.debug("Integer: {}", intObj);
        log.debug("Long   : {}", longObj);
        log.debug("Boolean: {}", boolObj);
        log.debug("String : {}", str);
        log.debug("Array  : {}", Arrays.asList(strArray));
    }
}
