package ru.ganichev.learn.rest.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;


import java.text.SimpleDateFormat;

import static ru.ganichev.learn.rest.web.json.JacksonObjectMapper.getMapper;

public class JsonUtil {
    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> String writeValue(T obj, SimpleDateFormat dateFormat) {
        try {
            return getMapper(dateFormat).writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }



}
