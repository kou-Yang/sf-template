package com.sf.common.base.common;

import com.sf.common.base.exception.BusinessException;
import com.sf.common.base.exception.CommonErrorEnum;
import com.sf.common.base.model.entity.BaseUser;

import java.util.Optional;

/**
 * @author ky
 * @description
 * @date 2023-12-13 19:23
 */
public class UserHolder {

    private static final ThreadLocal<BaseUser> threadLocal = new ThreadLocal<>();

    public static void set(BaseUser user) {
        threadLocal.set(user);
    }

    public static BaseUser get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static String getUserId() {
        return Optional.ofNullable(get()).map(BaseUser::getId).orElseThrow(() -> new BusinessException(CommonErrorEnum.NOT_LOGIN_ERROR));
    }
}
