package com.ThreadCount.demo.filter;


import java.util.List;
import java.util.Map;

public abstract class AbstractJson {
    private static JsonFactory defaultJsonFactory = new JacksonFactory();
    private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
    protected String datePattern = null;

    public AbstractJson() {
    }

    static void setDefaultJsonFactory(JsonFactory defaultJsonFactory) {
        if (defaultJsonFactory == null) {
            throw new IllegalArgumentException("defaultJsonFactory can not be null.");
        } else {
            defaultJsonFactory = defaultJsonFactory;
        }
    }

    static void setDefaultDatePattern(String defaultDatePattern) {
        if (defaultDatePattern != null && !defaultDatePattern.isEmpty()) {
            defaultDatePattern = defaultDatePattern;
        } else {
            throw new IllegalArgumentException("defaultDatePattern can not be blank.");
        }
    }

    public AbstractJson setDatePattern(String datePattern) {
        if (datePattern != null && !datePattern.isEmpty()) {
            this.datePattern = datePattern;
            return this;
        } else {
            throw new IllegalArgumentException("datePattern can not be blank.");
        }
    }

    public String getDatePattern() {
        return this.datePattern;
    }

    public String getDefaultDatePattern() {
        return defaultDatePattern;
    }

    public static AbstractJson getJson() {
        return defaultJsonFactory.getJson();
    }

    public abstract String toJson(Object var1);

    public abstract <T> T parse(String var1, Class<T> var2);

    public abstract <T> T parse(byte[] var1, Class<T> var2);

    public abstract <T> List<T> parseArray(String var1, Class<T> var2);

    public abstract <K, V> Map<K, V> parseMap(String var1, Class<K> var2, Class<V> var3);

    public abstract <K, V> Map<K, V> parseMap(byte[] var1, Class<K> var2, Class<V> var3);

    public abstract <K, V> List<Map<K, V>> parseMapArray(String var1, Class<K> var2, Class<V> var3);
}
