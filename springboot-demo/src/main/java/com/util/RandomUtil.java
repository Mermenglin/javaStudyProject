package com.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

    public static int getRandom(int i){
        Random random = new Random();
        return random.nextInt(i);
    }

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
