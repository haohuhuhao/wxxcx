package com.hh.wx.xcx.controller.exception;

import java.net.BindException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
	/**
     * 400 - Bad Request 参数绑定出错
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultVo<String> handleBindException(BindException e) {
        log.error("绑定参数出错", e);
        
        return ResultUtils.fail(HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST.value());
    }
 
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVo<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    	log.error("请求参数读取错误", e);
        return ResultUtils.fail(HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST.value());
    }
 
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultVo<String> handleValidationException(MethodArgumentNotValidException e) {
        log.error("请求参数验证失败", e);
        return ResultUtils.fail(HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST.value());
    }
 
    /**
     * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	log.error("请求方法不支持", e);
        return ResultUtils.fail(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),HttpStatus.METHOD_NOT_ALLOWED.value());
    }
 
    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResultVo<String> handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("content-type类型不支持", e);
        return ResultUtils.fail(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(),HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }
 
    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultVo<String> handleException(Exception e) {
        log.error("服务器内部错误", e);
        return ResultUtils.fail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
