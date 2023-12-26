package com.sf.common.web.exception;


import com.sf.common.base.common.BaseResponse;
import com.sf.common.base.common.ResultUtils;
import com.sf.common.base.exception.BusinessException;
import com.sf.common.base.exception.CommonErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 *
 * @author kouyang
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public BaseResponse<?> constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        log.debug("constraintViolationExceptionHandler", ex);
        StringBuilder errorMsg = new StringBuilder();
        ex.getConstraintViolations().forEach(x -> errorMsg.append(x.getPropertyPath()).append(":").append(x.getMessage()).append(";"));
        String message = errorMsg.toString();
        return ResultUtils.error(CommonErrorEnum.REQUEST_PARAM_INVALID_ERROR, message.substring(0, message.length() - 1));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("methodArgumentNotValidException", ex);
        StringBuilder errorMsg = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(x -> errorMsg.append(x.getField()).append(":").append(x.getDefaultMessage()).append(";"));
        String message = errorMsg.toString();
        return ResultUtils.error(CommonErrorEnum.REQUEST_PARAM_INVALID_ERROR, message.substring(0, message.length() - 1));
    }

    @ExceptionHandler(value = BindException.class)
    public BaseResponse<?> bindExceptionHandler(BindException ex) {
        log.error("bindExceptionHandler", ex);
        StringBuilder errorMsg = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(x -> errorMsg.append(x.getField()).append(":").append(x.getDefaultMessage()).append(";"));
        String message = errorMsg.toString();
        return ResultUtils.error(CommonErrorEnum.REQUEST_PARAM_INVALID_ERROR, message.substring(0, message.length() - 1));
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException ex) {
        log.error("businessException: " + ex.getMessage(), ex);
        return ResultUtils.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException ex) {
        log.error("runtimeException", ex);
        return ResultUtils.error(CommonErrorEnum.SYSTEM_ERROR, ex.getMessage());
    }
}
