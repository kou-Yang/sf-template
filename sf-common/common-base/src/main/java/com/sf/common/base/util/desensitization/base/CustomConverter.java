package com.sf.common.base.util.desensitization.base;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ky
 * @description 自定义数据转换器
 * @date 2024-04-17 16:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@JacksonAnnotationsInside
@JsonSerialize(using = DefaultConverter.class)
public @interface CustomConverter {

    Class<? extends BaseConverter> value();
}
