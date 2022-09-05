package com.ThreadCount.demo.logfilter;

public class JacksonFactory implements JsonFactory {
    private static final JacksonFactory FACTORY = new JacksonFactory();

    public JacksonFactory() {
    }

    public static JacksonFactory inst() {
        return FACTORY;
    }

    public AbstractJson getJson() {
        return new Jackson();
    }
}
