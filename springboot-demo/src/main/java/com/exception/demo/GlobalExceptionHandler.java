package com.exception.demo;

import com.exception.demo.entity.BaseResult;
import com.exception.demo.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理 Controller 层异常
 *
 * @Author: meimengling
 * @Date: 2019/9/24 16:01
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    BaseResult handleException(Exception e) {
        log.error(e.getMessage(), e);

        BaseResult response = new BaseResult();
        response.setCode("99999");
        response.setData("操作失败！");
        response.setMsg("系统错误！");
        return response;
    }

    /**
     * 处理所有业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    BaseResult handleBusinessException(BaseException e) {
        log.error(e.getMessage(), e);

        BaseResult response = new BaseResult();
        response.setCode(e.getMessage());
        response.setMsg("其他错误！");
        response.setCode("99991");
        return response;
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    BaseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);

        BaseResult response = new BaseResult();
        response.setData(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        response.setCode("99998");
        response.setMsg("参数错误！");
        return response;
    }

}
