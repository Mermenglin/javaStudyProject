package com.annotation.demo.annotation;

import com.annotation.demo.handle.ConstantValidatorHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: meimengling
 * @Date: 2019/9/27 16:21
 */
@Documented
//指定注解的处理类
@Constraint(validatedBy = {ConstantValidatorHandler.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Constant {

    String message() default "{constraint.default.const.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();

}