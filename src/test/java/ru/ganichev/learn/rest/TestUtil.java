package ru.ganichev.learn.rest;

import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.ganichev.learn.rest.web.json.JsonUtil.writeValue;

public class TestUtil {

    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeValue(expected));
    }
}
