package com.sf.common.base.common;

import com.sf.common.base.exception.CommonErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author kouyang
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(CommonErrorEnum commonErrorEnum) {
        this(commonErrorEnum.getCode(), null, commonErrorEnum.getMessage());
    }
}
