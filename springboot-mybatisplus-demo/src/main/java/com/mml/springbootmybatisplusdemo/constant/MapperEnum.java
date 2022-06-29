/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2022.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording,
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 *
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2022-06-27             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.mml.springbootmybatisplusdemo.constant;

import java.util.Map;
import java.util.TreeMap;

/**
 * TODO
 *
 * @author Mei.Mengling
 * @date 2022-06-27
 * @since 1.0.0
 */
public enum MapperEnum {

    USERMAPPER_DELETEIN("com.mml.springbootmybatisplusdemo.dao.UserMapper.deleteIn", "userServiceImpl", "deleteIn"),
    USERMAPPER_SELETEALL("com.mml.springbootmybatisplusdemo.dao.UserMapper.insert", "userServiceImpl", "insertUser"),
    USERMAPPER_GETBYID("com.mml.springbootmybatisplusdemo.dao.UserMapper.getById", "userServiceImpl", "getById"),
    USERMAPPER_SELECTBYUSER("com.mml.springbootmybatisplusdemo.dao.UserMapper.selectByUser", "userServiceImpl", "selectByUser"),
    USERMAPPER_UPDATE("com.mml.springbootmybatisplusdemo.dao.UserMapper.updateById", "userServiceImpl", "updateById"),
    USERMAPPER_UPDATEBYACCOUNT("com.mml.springbootmybatisplusdemo.dao.UserMapper.updateByAccount", "userServiceImpl", "updateByAccount"),
    USERMAPPER_SELECTBYACCOUNTS("com.mml.springbootmybatisplusdemo.dao.UserMapper.selectByAccounts", "userServiceImpl", "selectByAccounts"),
            ;


    private String mapperId;
    private String classz;
    private String methodName;

    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    public String getClassz() {
        return classz;
    }

    public void setClassz(String classz) {
        this.classz = classz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    MapperEnum(String mapperId, String classz, String methodName) {
        this.mapperId = mapperId;
        this.classz = classz;
        this.methodName = methodName;
    }

    private static Map<String, MapperEnum> map = new TreeMap<>();

    public static Map<String, MapperEnum> getMap() {
        if (map.isEmpty()) {
            synchronized (map) {
                if (map.isEmpty()) {
                    //取出时区枚举类的所有时区，去重转成MAP
                    for (MapperEnum s : MapperEnum.values()) {
                        if (null == map.get(s.getMapperId())) {
                            map.put(s.getMapperId(), s);
                        }
                    }
                }
            }
        }
        return map;
    }


    public static boolean checkMapperId(String mapperId) {
        if (map == null || map.isEmpty()) {
            getMap();
        }
        return map.containsKey(mapperId);
    }

    public static MapperEnum getRefletion(String mapperId) {
        if (map == null || map.isEmpty()) {
            getMap();
        }
        return map.get(mapperId);
    }
}
