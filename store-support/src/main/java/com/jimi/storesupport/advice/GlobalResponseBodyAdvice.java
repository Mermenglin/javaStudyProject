package com.jimi.onlinestoresupport.advice;

import com.jimi.onlinestoresupport.entity.Result;
import com.jimi.onlinestoresupport.exception.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.File;

/**
 * 对返回结果进行增强，统一返回格式
 */
@Slf4j
@ControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 将自定义类型进行封装，string类型不封装
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param converterType
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    @ResponseBody
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (null != o) {
            if (o instanceof Result || o instanceof String) {
                return o;
            } else if (o instanceof File) {
                return o;
            }
        }
        Result body = Result.success(o);

        return body;
    }
}
