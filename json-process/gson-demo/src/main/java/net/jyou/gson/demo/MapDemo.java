package net.jyou.gson.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Joey Chen
 * @created 2023/7/30 10:16
 */
@Slf4j
public class MapDemo {
    public static void main(String[] args) {

        log.info("----------Serialization----------");
        Gson gson = new Gson();
        Map<String, String> stringMap = new LinkedHashMap<>();
        stringMap.put("key", "value");
        stringMap.put(null, "null-entry");

        // Serialization
        String json = gson.toJson(stringMap); // ==> {"key":"value","null":"null-entry"}


        Map<Integer, Integer> intMap = new LinkedHashMap<>();
        intMap.put(2, 4);
        intMap.put(3, 6);

        // Serialization
        String intjson = gson.toJson(intMap); // ==> {"2":4,"3":6}

        log.debug("string Map >: {}", json);
        log.debug("int Map    >: {}", intjson);

        log.info("----------Deserialization----------");

        TypeToken<Map<String, String>> mapType = new TypeToken<>() {};
        String jsondemo = "{\"key\": \"value\"}";

        // Deserialization
        // Note: For older Gson versions it is necessary to use `mapType.getType()` as argument below,
        // this is however not type-safe and care must be taken to specify the correct type for the local variable
        Map<String, String> demoMap = gson.fromJson(jsondemo, mapType);
        log.debug("Deserialization Map :< {}", demoMap);

    }
}
