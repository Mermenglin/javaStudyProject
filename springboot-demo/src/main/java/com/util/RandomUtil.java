package com.util;

import java.util.Random;

public class RandomUtil {

    public static int getRandom(int i){
        Random random = new Random();
        return random.nextInt(i);
    }

}
