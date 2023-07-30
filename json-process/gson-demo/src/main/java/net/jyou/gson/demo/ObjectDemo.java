package net.jyou.gson.demo;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.jyou.gson.demo.objects.BagOfPrimitives;

/**
 * @author Joey Chen
 * @created 2023/7/30 9:46
 */
@Slf4j
public class ObjectDemo {
    public static void main(String[] args) {
        // Serialization
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        log.debug("Object: {}", json);

        // Deserialization
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(obj2);
        // ==> obj2 is just like obj
    }
}
