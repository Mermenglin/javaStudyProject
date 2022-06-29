package com.mml.springbootmybatisplusdemo.encrypt;

import cn.hutool.crypto.SecureUtil;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author huang.jiaming
 * @date 2021-10-18
 * @since 1.0.0
 */
public class AESUtil {
    private static final String KEYS = "a001s6Ha92nU81ju";

    /**
     * 加密
     *
     * @throws IllegalAccessException
     */
    public static String encrypt(String value) {
        return SecureUtil.aes(KEYS.getBytes(StandardCharsets.UTF_8)).encryptHex(value);

    }

    /**
     * 解密
     *
     * @throws IllegalAccessException
     */
    public static String decrypt(String value) {
        return SecureUtil.aes(KEYS.getBytes(StandardCharsets.UTF_8)).decryptStr(value);
    }

}
