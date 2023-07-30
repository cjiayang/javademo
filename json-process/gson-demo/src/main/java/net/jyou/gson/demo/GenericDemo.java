package net.jyou.gson.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

/**
 * @author Joey Chen
 * @created 2023/7/30 10:30
 */
@Slf4j
public class GenericDemo {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Foo<Bar> foo = new Foo<>();
        foo.value = new Bar();
        Type fooType = new TypeToken<Foo<Bar>>() {}.getType();
        String fooGson = gson.toJson(foo, fooType);
        log.info("----------Serialization----------");
        log.debug("Generic >: {}", fooGson);

        Foo<Bar> newFoo = gson.fromJson(fooGson, fooType);
        log.info("----------Deserialization----------");
        log.debug("Generic :< {}", newFoo);
    }
    @Data
    static class Foo<T> {
        T value;
    }

    @Data
    static class Bar {
        String b = "b";
        String a = "a";
        String r = "r";
    }
}
