package com.sf.common.base.util.desensitization.base;

import com.fasterxml.jackson.databind.BeanProperty;

/**
 * @author ky
 * @description 基础数据转换器
 * @date 2024-04-17 16:25
 */
public abstract class BaseConverter<T, D> {

    protected BeanProperty beanProperty;

    protected abstract D doConvert(T value);
}
