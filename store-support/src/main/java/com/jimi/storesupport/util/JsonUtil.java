package com.jimi.onlinestoresupport.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jimi.onlinestoresupport.util.jackson.JsonDateSerializer;
import com.jimi.onlinestoresupport.util.jackson.LocalDateDeserializer;
import com.jimi.onlinestoresupport.util.jackson.LocalDateTimeDeserializer;
import com.jimi.onlinestoresupport.util.jackson.LocalTimeDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author Mei.Mengling
 * @date 2022-08-24
 * @since 1.0.0
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper MAPPER = build();

    public static ObjectMapper build() {
        ObjectMapper om = new ObjectMapper();
        //取消默认转换timestamps形式
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 日期和时间格式化
        om.registerModule(javaTimeModule());
        return om;
    }

    public static JavaTimeModule javaTimeModule(){
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(Date.class, new JsonDateSerializer());
        javaTimeModule.addSerializer(Timestamp.class, new JsonDateSerializer());
        javaTimeModule.addSerializer(java.sql.Date.class, new JsonDateSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        return javaTimeModule;
    }

    public static String toString(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("toString: ", e);
        }
        return null;
    }


    public static <T> T parseObject(String json, Class<T> cls) {
        try {
            return MAPPER.readValue(json, cls);
        } catch (IOException e) {
            log.error("server error：parseObject() called with: json = 【{}】, cls = 【{}】", json, cls, e);
        }
        return null;
    }

    public static <T> List<T> parseArray(String json, TypeReference<List<T>> cls) {
        try {
            return MAPPER.readValue(json, cls);
        } catch (IOException e) {
            log.error("server error：parseObject() called with: json = 【{}】, cls = 【{}】", json, cls, e);
        }
        return null;
    }

    public static <T> List<T> parseArray(String json, Class<T> cls) {
        JavaType javaType = MAPPER.getTypeFactory().constructCollectionType(List.class, cls);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            log.error("server error：parseObject() called with: json = 【{}】, cls = 【{}】", json, cls, e);
        }
        return null;
    }

    public static <T> T parseObject(String json, TypeReference<T> cls) {
        try {
            return MAPPER.readValue(json, cls);
        } catch (IOException e) {
            log.error("server error：parseObject() called with: json = 【{}】, cls = 【{}】", json, cls, e);
        }
        return null;
    }

    public static Map<String, Object> parseMap(String json) {
        return parseObject(json, Map.class);
    }

    public static <K, V> Map<K, V> parseMap(String json, Class<K> keyType, Class<V> valueType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(Map.class, new Class[]{keyType, valueType});

        try {
            return MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            log.error("parseMap() called with params 【json = {}】, 【keyType = {}】, 【valueType = {}】", json, keyType, valueType, e);
        }
        return null;
    }


    public static boolean isJson(String str) {
        return isJsonObj(str) || isJsonArray(str);
    }

    public static boolean isJsonObj(String str) {
        if (str == null) {
            return false;
        } else {
            str = str.trim();
            if (str.isEmpty()) {
                return false;
            } else {
                return str.charAt(0) == '{' && str.charAt(str.length() - 1) == '}';
            }
        }
    }

    public static boolean isJsonArray(String str) {
        if (str == null) {
            return false;
        } else {
            str = str.trim();
            if (str.isEmpty()) {
                return false;
            } else {
                return str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']';
            }
        }
    }

}
