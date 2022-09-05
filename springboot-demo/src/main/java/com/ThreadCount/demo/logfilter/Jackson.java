package com.ThreadCount.demo.logfilter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jackson extends AbstractJson {
    private static boolean defaultGenerateNullValue = true;
    protected Boolean generateNullValue = null;
    protected ObjectMapper objectMapper = new ObjectMapper();

    public Jackson() {
        this.config();
    }

    protected void config() {
        this.objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        this.objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public static void setDefaultGenerateNullValue(boolean defaultGenerateNullValue) {
        defaultGenerateNullValue = defaultGenerateNullValue;
    }

    public Jackson setGenerateNullValue(boolean generateNullValue) {
        this.generateNullValue = generateNullValue;
        return this;
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public static Jackson getJson() {
        return new Jackson();
    }

    public String toJson(Object object) {
        try {
            String dp = this.datePattern != null ? this.datePattern : this.getDefaultDatePattern();
            if (dp != null) {
                this.objectMapper.setDateFormat(new SimpleDateFormat(dp));
            }

            boolean pnv = this.generateNullValue != null ? this.generateNullValue : defaultGenerateNullValue;
            if (!pnv) {
                this.objectMapper.setSerializationInclusion(Include.NON_NULL);
            }

            return this.objectMapper.writeValueAsString(object);
        } catch (Exception var4) {
            throw var4 instanceof RuntimeException ? (RuntimeException)var4 : new RuntimeException(var4);
        }
    }

    public <T> T parse(String jsonString, Class<T> type) {
        try {
            return this.objectMapper.readValue(jsonString, type);
        } catch (Exception var4) {
            throw var4 instanceof RuntimeException ? (RuntimeException)var4 : new RuntimeException(var4);
        }
    }

    public <T> T parse(byte[] jsonString, Class<T> type) {
        try {
            return this.objectMapper.readValue(jsonString, type);
        } catch (Exception var4) {
            throw var4 instanceof RuntimeException ? (RuntimeException)var4 : new RuntimeException(var4);
        }
    }

    public <T> List<T> parseArray(String jsonString, Class<T> type) {
        try {
            JavaType javaType = this.objectMapper.getTypeFactory().constructParametricType(List.class, new Class[]{type});
            return (List)this.objectMapper.readValue(jsonString, javaType);
        } catch (Exception var4) {
            throw var4 instanceof RuntimeException ? (RuntimeException)var4 : new RuntimeException(var4);
        }
    }

    public <K, V> Map<K, V> parseMap(String jsonString, Class<K> keyType, Class<V> valueType) {
        try {
            JavaType javaType = this.objectMapper.getTypeFactory().constructParametricType(Map.class, new Class[]{keyType, valueType});
            return (Map)this.objectMapper.readValue(jsonString, javaType);
        } catch (Exception var5) {
            throw var5 instanceof RuntimeException ? (RuntimeException)var5 : new RuntimeException(var5);
        }
    }

    public <K, V> Map<K, V> parseMap(byte[] jsonByte, Class<K> keyType, Class<V> valueType) {
        JavaType javaType = this.objectMapper.getTypeFactory().constructParametricType(Map.class, new Class[]{keyType, valueType});

        try {
            return (Map)this.objectMapper.readValue(jsonByte, javaType);
        } catch (IOException var6) {
            throw new RuntimeException(var6);
        }
    }

    public <K, V> List<Map<K, V>> parseMapArray(String jsonStr, Class<K> keyType, Class<V> valType) {
        JavaType javaType = this.objectMapper.getTypeFactory().constructParametricType(List.class, new Class[]{Map.class});

        try {
            List<Map> list = (List)this.objectMapper.readValue(jsonStr, javaType);
            List<Map<K, V>> values = new ArrayList();
            list.forEach((row) -> {
                values.add(this.parseMap(this.toJson(row), keyType, valType));
            });
            return values;
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
