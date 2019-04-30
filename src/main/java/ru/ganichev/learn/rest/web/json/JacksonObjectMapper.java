package ru.ganichev.learn.rest.web.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;


public class JacksonObjectMapper extends ObjectMapper {

    private static final ObjectMapper MAPPER = new JacksonObjectMapper();

    private JacksonObjectMapper() {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static ObjectMapper getMapper (SimpleDateFormat dateFormat) {
        JacksonObjectMapper objectMapper = new JacksonObjectMapper();
        objectMapper.setDateFormat(dateFormat);
        return objectMapper;
    }
}
