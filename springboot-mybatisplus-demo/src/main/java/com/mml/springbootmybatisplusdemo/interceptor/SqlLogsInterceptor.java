package com.mml.springbootmybatisplusdemo.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson2.JSON;
import com.mml.springbootmybatisplusdemo.constant.MapperEnum;
import com.mml.springbootmybatisplusdemo.utils.ReflectionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.text.DateFormat;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Component
public class SqlLogsInterceptor implements Interceptor {

    @Autowired
    ReflectionUtil reflectionUtil;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StopWatch sw = new StopWatch("mybatis interceptor");
        sw.start();

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        List<ResultMap> resultMaps = mappedStatement.getResultMaps();
        Class<?> type = null;
        if (!resultMaps.isEmpty()) {
            ResultMap resultMap = resultMaps.get(0);
            type = resultMap.getType();
        }
        String mappedStatementId = mappedStatement.getId();

        Object result = null;

        if (checkId(mappedStatementId)) {
            SqlCommandType commandType = mappedStatement.getSqlCommandType();
            Object parameter = null;
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }

            Map<String, Object> parameterMap = getParameterMap(mappedStatement, parameter);
            MapperEnum refletion = MapperEnum.getRefletion(mappedStatementId);
            String classz = refletion.getClassz();
            String methodName = refletion.getMethodName();
            switch (commandType) {
                case SELECT:
                    result = selectProcess(type, parameterMap, classz, methodName);
                    break;
                case INSERT:
                case UPDATE:
                case DELETE:
                    result = process(type, parameterMap, classz, methodName);
                    break;
                default:
                    result = invocation.proceed();
            }
        } else {
            result = invocation.proceed();
        }

        sw.stop();
        log.info("interceptor cost {}ms", sw.getTotalTimeMillis());

        return result;
    }

    private Map<String, Object> getParameterMap(MappedStatement mappedStatement, Object parameter) {
        Configuration configuration = mappedStatement.getConfiguration();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        Map<String, Object> result = new HashMap<>();
        boolean flag = false;
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                if (parameterMappings.size() > 0) {
                    ParameterMapping parameterMapping = parameterMappings.get(0);
                    mapPutProceessName(result, parameterMapping.getProperty(), getParameterValue(parameterObject));
                }
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        mapPutProceessName(result, propertyName, getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                if (parameterObject instanceof Map) {
                    Map resultMap = (HashMap) parameterObject;
                    Object obj;
                    Iterator iterator = resultMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        String next = (String) iterator.next();
                        obj = resultMap.get(next);
                        if (obj instanceof List && !"param1".equals(next) && !"list".equals(next)) {
                            result.put(next, JSON.toJSONString(obj));
                        } else if (obj instanceof Set && !"collection".equals(next)) {
                            result.put(next, JSON.toJSONString(obj));
                        } else if ("param1".equals(next)) {
                            result = BeanUtil.beanToMap(obj);
                        }
                    }
                }
                Class<?> parameterObjectClass = parameterObject.getClass();
                if (!isJavaClass(parameterObjectClass)) {
                    result = BeanUtil.beanToMap(parameterObject);
                }
            }
        }


        return result;
    }

    public static boolean isJavaClass(Class<?> classz) {
        return classz != null && classz.getClassLoader() == null;
    }

    private void mapPutProceessName(Map map, String name, Object value) {
        if (name.contains(".")) {
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf + 1 < name.length()) {
                name = name.substring(lastIndexOf + 1);
            }
        }
        map.put(name, value);
    }

    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = obj.toString();
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = formatter.format(new Date());
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    /**
     * todo 需要根据具体业务获取
     * 校验是否是是满足的 mapperId
     *
     * @param mapperId
     * @return
     */
    private boolean checkId(String mapperId) {
//        return false;
        return MapperEnum.checkMapperId(mapperId);
    }

    private Object selectProcess(Class type, Map<String, Object> parameterMap, String classz, String methodName) {

        try {
            Object o = reflectionUtil.invokeService(classz, methodName, parameterMap);
            if (o instanceof List) {
                List<Map> list = (List<Map>) o;
                List result = new ArrayList();
                for (Map map : list) {
                    result.add(BeanUtil.mapToBean(map, type, false, null));
                }
                return result;
            }
            if (o instanceof Map) {
                List result = new ArrayList();
                Map map = (Map) o;
                result.add(BeanUtil.mapToBean(map, type, false, null));
                return result;
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object process(Class<?> type, Map<String, Object> parameterMap, String classz, String methodName) {
        log.info("parameterMap:{}, methodName:{}, classz:{}", parameterMap, methodName, classz);
        try {
            Object o = reflectionUtil.invokeService(classz, methodName, parameterMap);
            if (o instanceof String) {
                if (type == null) {
                    type = Integer.class;
                }
                String s = (String) o;
                if (s != "" && s != "null") {
                    o = Convert.convert(type, o);
                } else {
                    return 0;
                }
            }
            return o;
        } catch (Exception e) {
            log.error("失败, parameterMap:{}, methodName:{}, classz:{}", parameterMap, methodName, classz);
            e.printStackTrace();
        }
        return null;
    }
}