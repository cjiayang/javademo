package net.jyou;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonFlattener {

    private final ObjectMapper objectMapper;

    public JsonFlattener() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 将JSON字符串扁平化为键值对
     * @param jsonString JSON字符串
     * @return 扁平化的键值对Map
     * @throws Exception 如果解析失败
     */
    public Map<String, Object> flatten(String jsonString) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonString);
        Map<String, Object> flattenedMap = new HashMap<>();
        flattenJsonNode("", rootNode, flattenedMap);
        return flattenedMap;
    }

    /**
     * 递归扁平化JSON节点
     * @param currentPath 当前路径
     * @param jsonNode 当前JSON节点
     * @param flattenedMap 存储结果的Map
     */
    private void flattenJsonNode(String currentPath, JsonNode jsonNode, Map<String, Object> flattenedMap) {
        if (jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
            String prefix = currentPath.isEmpty() ? "" : currentPath + "->";

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                flattenJsonNode(prefix + field.getKey(), field.getValue(), flattenedMap);
            }
        } else if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            String prefix = currentPath.isEmpty() ? "" : currentPath;

            for (int i = 0; i < arrayNode.size(); i++) {
                flattenJsonNode(prefix + "[" + i + "]", arrayNode.get(i), flattenedMap);
            }
        } else if (jsonNode.isValueNode()) {
            if (jsonNode.isTextual()) {
                flattenedMap.put(currentPath, jsonNode.textValue());
            } else if (jsonNode.isNumber()) {
                flattenedMap.put(currentPath, jsonNode.numberValue());
            } else if (jsonNode.isBoolean()) {
                flattenedMap.put(currentPath, jsonNode.booleanValue());
            } else if (jsonNode.isNull()) {
                flattenedMap.put(currentPath, null);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String json = """
                {
                    "management_version": "3.13.7",
                    "rates_mode": "basic",
                    "sample_retention_policies": {
                        "global": [
                            600,
                            3600,
                            28800,
                            86400
                        ],
                        "basic": [
                            600,
                            3600
                        ],
                        "detailed": [
                            600
                        ]
                    },
                    "statistics_db_event_queue": 0,
                    "node": "rabbit@rabbit",
                    "listeners": [
                        {
                            "node": "rabbit@rabbit",
                            "protocol": "amqp",
                            "ip_address": "::",
                            "port": 5672,
                            "socket_opts": {
                                "backlog": 128,
                                "nodelay": true,
                                "linger": [
                                    true,
                                    0
                                ],
                                "exit_on_close": false
                            }
                        }
                    ],
                    "contexts": [
                        {
                            "ssl_opts": [],
                            "node": "rabbit@rabbit",
                            "description": "RabbitMQ Management",
                            "path": "/",
                            "cowboy_opts": "[{sendfile,false}]",
                            "port": "15672"
                        },
                        {
                            "ssl_opts": [],
                            "node": "rabbit@rabbit",
                            "description": "RabbitMQ Prometheus",
                            "path": "/",
                            "port": "15692",
                            "protocol": "'http/prometheus'",
                            "cowboy_opts": "[{sendfile,false}]"
                        }
                    ]
                }
                """;

        JsonFlattener flattener = new JsonFlattener();
        Map<String, Object> flattened = flattener.flatten(json);

        flattened.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
