package com.sf.common.base.common;

import com.sf.common.base.exception.CommonErrorEnum;

/**
 * 返回工具类
 *
 * @author kouyang
 */
public class ResultUtils {

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(CommonErrorEnum.SUCCESS);
    }

    /**
     * 成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, data, "ok");
    }

    /**
     * 失败
     * @param commonErrorEnum
     * @return
     */
    public static BaseResponse error(CommonErrorEnum commonErrorEnum) {
        return new BaseResponse<>(commonErrorEnum);
    }

    /**
     * 失败
     * @param code
     * @param message
     * @return
     */
    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }

    /**
     * 失败
     * @param commonErrorEnum
     * @return
     */
    public static BaseResponse error(CommonErrorEnum commonErrorEnum, String message) {
        return new BaseResponse(commonErrorEnum.getCode(), null, message);
    }
}
