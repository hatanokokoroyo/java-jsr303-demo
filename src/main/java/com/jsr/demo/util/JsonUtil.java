package com.jsr.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJsonString(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
