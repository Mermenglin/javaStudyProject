package com.conver.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

/**
 * @Author: meimengling
 * @Date: 2022/05/31 11:05
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public static void copyPropertiesIgnoreNullProperty(Object source, Object target) throws BeansException {
        copyProperties(source, target, getNullPropertyNames(source));
    }

}
