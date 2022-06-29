package com.mml.springbootmybatisplusdemo.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLInListExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.alibaba.druid.sql.ast.statement.SQLJoinTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.*;

@Slf4j
//@AllArgsConstructor
//@Intercepts({
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
//})
//@Component
public class SqlLogsInterceptor_bak implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        SqlEntity entity = new SqlEntity();
        entity.setId(mappedStatement.getId());
        entity.setType(mappedStatement.getSqlCommandType());
        /*
        String sql = getSql(parameter, mappedStatement);
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement = parser.parseStatement();
        if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
            SQLInsertStatement sqlInsertStatement = (SQLInsertStatement) sqlStatement;
            SQLName tableName = sqlInsertStatement.getTableName();
            String simpleName = tableName.getSimpleName();
            entity.setTableName(simpleName);
            List<SQLExpr> values = sqlInsertStatement.getValues().getValues();
            List<SQLExpr> columns = sqlInsertStatement.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                SQLExpr column = columns.get(i);
                SQLExpr value = values.get(i);
                entity.setResult(column.toString(), value.toString());
            }

        } else if (SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
            SQLUpdateStatement sqlUpdateStatement = (SQLUpdateStatement) sqlStatement;
            SQLName tableName = sqlUpdateStatement.getTableName();
            String simpleName = tableName.getSimpleName();
            entity.setTableName(simpleName);
            List<SQLUpdateSetItem> items = sqlUpdateStatement.getItems();
            for (SQLUpdateSetItem item : items) {
                entity.setResult(item.getColumn().toString(), item.getValue().toString());
            }
            SQLExpr where = sqlUpdateStatement.getWhere();
            whereProcess(entity, where);
        } else if (SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) sqlStatement;
            SQLSelect select = sqlSelectStatement.getSelect();
            MySqlSelectQueryBlock queryBlock = (MySqlSelectQueryBlock) select.getQuery();
            SQLTableSource from = queryBlock.getFrom();
            fromProcess(entity, from);

            List<SQLSelectItem> selectList = queryBlock.getSelectList();
            for (SQLSelectItem sqlSelectItem : selectList) {
                entity.setResult(sqlSelectItem.getExpr().toString());
            }

            SQLExpr where = queryBlock.getWhere();
            whereProcess(entity, where);
        } else if (SqlCommandType.DELETE.equals(mappedStatement.getSqlCommandType())) {
            SQLDeleteStatement sqlDeleteStatement = (SQLDeleteStatement) sqlStatement;
            SQLName tableName = sqlDeleteStatement.getTableName();
            String simpleName = tableName.getSimpleName();
            entity.setTableName(simpleName);
            SQLExpr where = sqlDeleteStatement.getWhere();
            whereProcess(entity, where);
        }
        */

        log.info("sql entity:{}", JSON.toJSONString(entity));
        Object proceed = invocation.proceed();
        log.info("result class: {}", proceed.getClass());
        return proceed;
    }

    /**
     * 从from中获取tableName
     *
     * @param entity
     * @param from
     */

    private void fromProcess(SqlEntity entity, SQLTableSource from) {
        if (from instanceof SQLJoinTableSource) {
            SQLJoinTableSource joinTableSource = (SQLJoinTableSource) from;
            SQLTableSource leftTable = joinTableSource.getLeft();
            entity.setTableName(leftTable.toString());
            SQLTableSource right = joinTableSource.getRight();
            fromProcess(entity, right);
        } else {
            String simpleName = from.toString();
            entity.setTableName(simpleName);
        }
    }

    /**
     * 获取where条件
     *
     * @param entity
     * @param where
     */

    private void whereProcess(SqlEntity entity, SQLExpr where) {
        if (where instanceof SQLInListExpr) {
            SQLInListExpr whereInList = (SQLInListExpr) where;
            List<SQLExpr> targetList = whereInList.getTargetList();
            for (SQLExpr sqlExpr : targetList) {
                String value = sqlExpr.toString();
                SQLInListExpr parent = (SQLInListExpr) sqlExpr.getParent();
                String key = parent.getExpr().toString();
                entity.setWhere(key, value);
            }
        } else if (where instanceof SQLBinaryOpExpr) {
            SQLBinaryOpExpr expr = (SQLBinaryOpExpr) where;
            SQLBinaryOperator operator = expr.getOperator();
            if (operator.equals(SQLBinaryOperator.Equality)) {
                SQLExpr right = expr.getRight();
                if (right instanceof SQLMethodInvokeExpr) {
                    // sql中包含方法
                    entity.setWhere(expr.getLeft().toString(), right.toString());
                } else {
                    entity.setWhere(expr.getLeft().toString(), right.toString());
                }
            }
        }
    }

    /**
     * 替换操作，把实际执行的sql给拼出来
     *
     * @param paramter
     * @param mappedStatement
     * @return
     */
    private String getSql(Object paramter, MappedStatement mappedStatement) {
        BoundSql boundSql = mappedStatement.getBoundSql(paramter);
        Configuration configuration = mappedStatement.getConfiguration();
        return showSql(configuration, boundSql);
    }

    private static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //替换空格、换行、tab缩进等
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    private Map<String, Object> getParameterMap(MappedStatement mappedStatement, Object parameter) {
        Configuration configuration = mappedStatement.getConfiguration();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        Map<String, Object> result = new HashMap<>();

       if (parameterObject != null ) {
           TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (parameterObject instanceof Map) {
                Map resultMap = (HashMap) parameterObject;
                Object obj;
                Iterator iterator = resultMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String next = (String) iterator.next();
                    obj = resultMap.get(next);
                    // 表示是批量
                    if (obj instanceof List && !"param1".equals(next) && !"list".equals(next)) {
                        result.put(next, JSON.toJSONString(obj));
                    } else if (obj instanceof Set && !"collection".equals(next)) {
                        result.put(next, JSON.toJSONString(obj));
                    } else if ("param1".equals(next)) {
                        result = BeanUtil.beanToMap(obj);
                    }
                }
            } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                if (parameterMappings.size() > 0) {
                    ParameterMapping parameterMapping = parameterMappings.get(0);
                    result.put(parameterMapping.getProperty(), getParameterValue(parameterObject));
                }
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        result.put(propertyName, getParameterValue(obj));
                    }
                }
            }
        }
        return result;
    }

    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value.replace("$", "\\$");
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties0) {
    }

    /**
     * 从handler中获取 parameterObject
     *
     * @param statementHandler
     * @return
     * @throws Exception
     */

    private Object getParamter(StatementHandler statementHandler) throws Exception {
        // 获取参数对像，即 mapper 中 paramsType 的实例
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        Object parameterObject = parameterHandler.getParameterObject();
        return parameterObject;
    }

    /**
     * 从parameterObject 获取对应的参数
     *
     * @param parameterObject
     * @return
     * @throws IllegalAccessException
     */

    private Map<String, Object> paramHandler(Object parameterObject) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
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
                        if (!CollectionUtils.isEmpty(pramList) && !Objects.isNull(pramList.get(0))) {
                            for (Object o : pramList) {
                                parseObpject(o, map);
                            }
                        }
                    } else if ("param1".equals(next)) {
                        parseObpject(obj, map);
                    }
                }
            } else {
                parseObpject(parameterObject, map);
            }
        }
        return map;
    }

    /**
     * 反射获取字段名和值
     *
     * @param parameterObject
     * @param map
     * @throws IllegalAccessException
     */

    private void parseObpject(Object parameterObject, Map<String, Object> map) throws IllegalAccessException {

        Class<?> parameterObjectClass = parameterObject.getClass();
        //取出当前当前类所有字段，传入加密方法
        Field[] declaredFields = parameterObjectClass.getDeclaredFields();
        Field[] declaredFields1 = parameterObjectClass.getSuperclass().getDeclaredFields();
        if (declaredFields1.length > 0) {
            declaredFields = addFiled(declaredFields, declaredFields1);
        }
        for (Field field : declaredFields) {
            getField(parameterObject, field, map);
        }
    }

    public Field[] addFiled(Field[] data1, Field[] data2) {
        Field[] data3 = new Field[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }

    private void getField(Object parameterObject, Field field, Map<String, Object> map) throws IllegalAccessException {
        //取出所有被EncryptDecryptField注解的字段
        field.setAccessible(true);
        Object object = field.get(parameterObject);
        map.put(field.getName(), object);
    }

}