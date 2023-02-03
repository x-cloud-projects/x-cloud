package com.xfeel.cloud.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * JSON工具类
 * @author Admin
 */
public class JsonUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        //NON_NULL,NON_EMPTY,NON_DEFAULT,NON_ABSENT
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        OBJECT_MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true);

        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dtf));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dtf));
        OBJECT_MAPPER.registerModule(javaTimeModule);
    }

    /**
     * 对象转JSON字符串
     * @param json
     * @return
     */
    public static String toJsonString(Object json) {
        if (json == null) {
            return null;
        }
        if (json instanceof String) {
            return (String) json;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转JSON字符串（美化）
     * @param jsonObj
     * @return
     */
    public static String toPrettyJsonString(Object jsonObj) {
        if (jsonObj == null) {
            return null;
        }
        if (jsonObj instanceof String) {
            return (String) jsonObj;
        }
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JSON字符串转对象
     * @param json
     * @param clz
     * @return
     * @param <T>
     */
    public static <T> T parseJson(String json, Class<T> clz) {
        if (json == null || clz == null) {
            return null;
        }

        if (clz.equals(String.class)) {
            return (T) json;
        }

        try {
            return OBJECT_MAPPER.readValue(json, clz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JSON字符串转对象（泛型）
     * @param json
     * @param typeReference
     * @return
     * @param <T>
     */
    public static <T> T parseJson(String json, TypeReference<T> typeReference) {
        if (json == null || typeReference == null) {
            return null;
        }

        if (typeReference.getType().equals(String.class)) {
            return (T) json;
        }

        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
