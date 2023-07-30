package net.jyou.gson.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Joey Chen
 * @created 2023/7/30 10:03
 */
@Slf4j
public class CollectionDemo {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Collection<Integer> ints = Arrays.asList(1,2,3,4,5);

        // Serialization
        String json = gson.toJson(ints);  // ==> [1,2,3,4,5]
        log.info("----------Serialization----------");
        log.debug("Collection >: {}", json);

        // Deserialization
        TypeToken<Collection<Integer>> collectionType = new TypeToken<>() {};
        // Note: For older Gson versions it is necessary to use `collectionType.getType()` as argument below,
        // this is however not type-safe and care must be taken to specify the correct type for the local variable
        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
        log.info("----------Deserialization----------");
        log.debug("Collection :< {}", ints2);
        // ==> ints2 is same as ints
    }
}
