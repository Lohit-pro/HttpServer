package org.socgen.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    private static ObjectMapper myObjectMapper = defaultMapper();

    private static ObjectMapper defaultMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return om;
    }

    public static JsonNode parse(String jsonSrc) throws IOException {
        return myObjectMapper.readTree(jsonSrc);
    }

    public static <T>T fromJsonToObject(JsonNode node, Class<T> tClass) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, tClass);
    }

    public static JsonNode fromObjectToJson(Object obj){
        return myObjectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode obj) throws JsonProcessingException {
        return generateJson(obj, false);
    }

    public static String stringifyPretty(JsonNode obj) throws JsonProcessingException {
        return generateJson(obj, true);
    }

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter writer = myObjectMapper.writer();
        if (pretty){
            writer = writer.with(SerializationFeature.INDENT_OUTPUT);
        }

        return writer.writeValueAsString(o);
    }

}
