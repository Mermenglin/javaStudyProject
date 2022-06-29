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

/**
 * TODO
 *
 * @author huang.jiaming
 * @date 2021-10-18
 * @since 1.0.0
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.*;

@Slf4j
@Component
@Intercepts(@Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}))
public class EncryptInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!(invocation.getTarget() instanceof ParameterHandler)) {
            return invocation.proceed();
        }
        //@Signature 指定了 type= parameterHandler 后，这里的 invocation.getTarget() 便是parameterHandler
        //若指定ResultSetHandler ，这里则能强转为ResultSetHandler
        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        // 获取参数对像，即 mapper 中 paramsType 的实例
        Object parameterObject = parameterHandler.getParameterObject();
        paramHandler(parameterObject, true);
        Object proceed = invocation.proceed();
        paramHandler(parameterObject, false);
        return proceed;
    }

    private void paramHandler(Object parameterObject, boolean b) throws IllegalAccessException {
        if (parameterObject != null) {
            if (parameterObject instanceof Map) {
                Map resultMap = (HashMap) parameterObject;
                Object obj;
                Iterator iterator = resultMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String next = (String) iterator.next();
                    obj = resultMap.get(next);
                    if (obj instanceof List && !"param1".equals(next) && !"list".equals(next)) {
                        List pramList = (List) obj;
                        if (!CollectionUtils.isEmpty(pramList) && !Objects.isNull(pramList.get(0)) && needToDecrypt(pramList.get(0))) {
                            for (Object o : pramList) {
                                encryObject(o, b);
                            }
                        }
                    } else if ("param1".equals(next)) {
                        encryObject(obj, b);
                    }
                }
            } else {
                encryObject(parameterObject, b);
            }
        }
    }

    private void encryObject(Object parameterObject, Boolean flag) throws IllegalAccessException {
        Class<?> parameterObjectClass = parameterObject.getClass();
        //校验该实例的类是否被@SensitiveData所注解
        if (parameterObjectClass.isAnnotationPresent(SensitiveData.class)) {
            //取出当前当前类所有字段，传入加密方法
            Field[] declaredFields = parameterObjectClass.getDeclaredFields();
            Field[] declaredFields1 = parameterObjectClass.getSuperclass().getDeclaredFields();
            if (parameterObjectClass.getSuperclass().isAnnotationPresent(SensitiveData.class)) {
                if (declaredFields1.length > 0) {
                    declaredFields = addFiled(declaredFields, declaredFields1);
                }
            }
            for (Field field : declaredFields) {
                encryField(parameterObject, field, flag);
            }
        }
    }

    private void encryField(Object parameterObject, Field field, Boolean encf) throws IllegalAccessException {
        //取出所有被EncryptDecryptField注解的字段
        SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
        if (!Objects.isNull(sensitiveField)) {
            field.setAccessible(true);
            Object object = field.get(parameterObject);
            //暂时只实现String类型的加密
            if (object instanceof String) {
                try {
                    String value = (String) object;
                    if (!"".equals(value)) {
                        if (encf) {
                            //加密  这里我使用hutool的AES加密工具
                            field.set(parameterObject, AESUtil.encrypt(value));
                            log.info("加密字段:" + value + "加密后:" + AESUtil.encrypt(value));
                        } else {
                            //加密  这里我使用hutool的AES加密工具
                            field.set(parameterObject, AESUtil.decrypt(value));
                            log.info("解密:" + value + "解密后:" + AESUtil.decrypt(value));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (Objects.isNull(object)) {
                    try {
                        if (StringUtils.hasLength(sensitiveField.type())) {
                            Class clz = Class.forName(sensitiveField.type());
                            if (clz.isAnnotationPresent(SensitiveData.class)) {
                                Field[] declaredFields = clz.getDeclaredFields();
                                for (Field declaredField : declaredFields) {
                                    encryField(object, declaredField, encf);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    /**
     * 切记配置，否则当前拦截器不会加入拦截器链
     */
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    //自定义配置写入，没有自定义配置的可以直接置空此方法
    @Override
    public void setProperties(Properties properties) {
    }

    public Field[] addFiled(Field[] data1, Field[] data2) {
        Field[] data3 = new Field[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }

    private boolean needToDecrypt(Object object) {
        Class<?> objectClass = object.getClass();
        return objectClass.isAnnotationPresent(SensitiveData.class);
    }
}
