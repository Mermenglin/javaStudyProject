package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public static String getTime(){
        return format.format(new Date());
    }

}
