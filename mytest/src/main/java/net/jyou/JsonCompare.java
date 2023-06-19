package net.jyou;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Joey Chen
 * @created 2023/4/19 10:24
 */
@Slf4j
public class JsonCompare {
    public static void main(String[] args) {

        Set<String> localIdSet = extractIds("C:\\Users\\admin\\Desktop\\local_entity.json", "author_entity_id");
        Set<String> remoteIdSet = extractIds("C:\\Users\\admin\\Desktop\\remote_entity.json", "originalDescriminator");
        log.info("localIdSet: {}", localIdSet.size());
        log.info("remoteIdSet: {}", remoteIdSet.size());
        Set<String> entitiesForDelete = new HashSet<>(localIdSet);
        boolean b = entitiesForDelete.removeAll(remoteIdSet);
        log.info("localIdSet: {}", localIdSet.size());
    }


    public static Set<String> extractIds(String fileName, String fieldName) {
        File file = new File(fileName);

        Set<String> localSet = new HashSet<>();
        StringBuilder localJsontext = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.lines()
                    .forEach(localJsontext::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray localJsonArray = JSON.parseArray(localJsontext.toString());
        for (Object obj : localJsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            String entityId = jsonObject.getString(fieldName);
            localSet.add(entityId);
        }
        return localSet;
    }
}
