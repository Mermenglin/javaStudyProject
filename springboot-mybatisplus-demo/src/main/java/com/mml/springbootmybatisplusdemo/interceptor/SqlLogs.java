package com.mml.springbootmybatisplusdemo.interceptor;

import java.lang.annotation.*;

/**
 *
 * @author Mei.Mengling
 * @date 2022-06-16
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SqlLogs {
    /**
     * 是否打印sql
     */
    public boolean hasSqlLog() default false;
}
