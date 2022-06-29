/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2021.
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
 * 2021-10-18             huang.jiaming   Create the class
 * http://www.jimilab.com/
 */


package com.mml.springbootmybatisplusdemo.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * TODO
 *
 * @author huang.jiaming
 * @date 2021-10-18
 * @since 1.0.0
 */
@Slf4j
@Component
@Intercepts(@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}))
public class DecryptInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //取出查询的结果
        Object resultObject = invocation.proceed();
        if (Objects.isNull(resultObject)) {
            return null;
        }
        //基于selectList
        if (resultObject instanceof ArrayList) {
            ArrayList resultList = (ArrayList) resultObject;
            if (!CollectionUtils.isEmpty(resultList) && Objects.nonNull(resultList.get(0)) && needToDecrypt(resultList.get(0))) {
                for (Object result : resultList) {
                    //逐一解密
                    decrypt(result);
                }
            }
            //基于selectOne
        } else {
            if (needToDecrypt(resultObject)) {
                decrypt(resultObject);
            }
        }
        return resultObject;
    }

    private boolean needToDecrypt(Object object) {
        Class<?> objectClass = object.getClass();
        return objectClass.isAnnotationPresent(SensitiveData.class);
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


    public <T> T decrypt(T result) throws IllegalAccessException {
        //取出resultType的类
        Class<?> resultClass = result.getClass();
        Field[] declaredFields1 = resultClass.getSuperclass().getDeclaredFields();
        Field[] declaredFields = resultClass.getDeclaredFields();
        if (resultClass.getSuperclass().isAnnotationPresent(SensitiveData.class)) {
            if (declaredFields1.length > 0) {
                declaredFields = addFiled(declaredFields, declaredFields1);
            }
        }
        for (Field field : declaredFields) {
            //取出所有被SensitiveField注解的字段
            SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
            if (!Objects.isNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(result);
                //只支持String的解密
                try {
                    if (object instanceof String) {
                        String value = (String) object;
                        if (!"".equals(value)) {
                            //对注解的字段进行逐一解密
                            log.info("解密字段:" + value);
                            field.set(result, AESUtil.decrypt(value));
                        }
                    } else {
                        if (!Objects.isNull(object)) {
                            try {
                                Class clz = Class.forName(sensitiveField.type());
                                if (clz.isAnnotationPresent(SensitiveData.class)) {
                                    decrypt(object);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                } catch (Exception e) {
                    log.error("解密失败");
                    field.set(result, "解密失败");
                }
            }
        }
        return result;
    }


    public Field[] addFiled(Field[] data1, Field[] data2) {
        Field[] data3 = new Field[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }
}
