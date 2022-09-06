package com.jimi.onlinestoresupport.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * 针对时间类型，进行特定逻辑处理
 *
 * @author meimengling
 * @date 2022/6/2 10:24
 */
public class JsonDateSerializer extends JsonSerializer<Object> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (object == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (object instanceof Date) {
            Date date = (Date) object;
            jsonGenerator.writeString(String.valueOf(date.getTime()));
        } else if (object instanceof java.sql.Date) {
            java.sql.Date date = (java.sql.Date) object;
            jsonGenerator.writeString(String.valueOf(date.getTime()));
        } else if (object instanceof LocalDateTime) {
            LocalDateTime date = (LocalDateTime) object;
            // 0时区
            long epochSecond = date.toInstant(ZoneOffset.ofHours(0)).getEpochSecond();
            jsonGenerator.writeString(String.valueOf(epochSecond));
        } else {
            jsonGenerator.writeString(String.valueOf(object));
        }
    }
}
