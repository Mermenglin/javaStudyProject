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
 * 2022-09-06             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.jimi.onlinestoresupport.advice;

import com.jimi.onlinestoresupport.entity.Result;
import com.jimi.onlinestoresupport.exception.ApiException;
import com.jimi.onlinestoresupport.exception.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author Mei.Mengling
 * @date 2022-09-06
 * @since 1.0.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获自定义异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public Result ApiExceptionHandler(HttpServletRequest request, ApiException e) {
        log.error("[{}]接口异常, message:", request.getRequestURI(), e);
        Result result = new Result();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        result.setData(e.getData());
        return result;
    }

    /**
     * 捕获 Validated 抛出的异常，参数为空信息
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindExceptionHandler(HttpServletRequest request, BindException e) {
        log.error("[{}]接口异常, message:", request.getRequestURI(), e.getMessage());
        Result Result = new Result();
        Result.convert(ResultCodeEnum.ILLEGAL_PARAMETER);
        return Result;
    }

    /**
     * 捕获 Validated 抛出的异常，参数为空信息
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result argumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("[{}]接口异常, message:", request.getRequestURI(), e.getMessage());
        Result Result = new Result();
        Result.convert(ResultCodeEnum.ILLEGAL_PARAMETER);
        return Result;
    }

    /**
     * 捕获请求入参json转换成对象时失败的错误
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result httpMessageNotReadableExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException e) {
        log.error("[{}]接口异常, message:", request.getRequestURI(), e.getMessage());
        Result Result = new Result();
        Result.convert(ResultCodeEnum.ILLEGAL_PARAMETER);
        return Result;
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("[{}]接口异常, message:", request.getRequestURI(), e);
        Result Result = new Result();
        Result.convert(ResultCodeEnum.SYSTEM_EXCEPTION);
        return Result;
    }

    /**
     * 构建并绑定返回结果，输出 @NotBlank 和 @NotNull 中 message 信息
     *
     * @param bindingResult 需要处理的错误信息
     * @return 错误信息
     */
    private String buildBindingResult(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        List<ObjectError> errors = bindingResult.getAllErrors();
        errors.stream()
                .forEach(error -> {
                    if (errorMessage.length() > 0) {
                        errorMessage.append(",");
                    }
                    String errorInfo = error.getDefaultMessage();
                    errorMessage.append(errorInfo);
                });
        return errorMessage.toString();
    }
}
