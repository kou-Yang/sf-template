package com.sf.common.base.util.desensitization.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sf.common.base.util.desensitization.annotation.impl.IdCardConverterImpl;
import com.sf.common.base.util.desensitization.base.CustomConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ky
 * @description 身份证脱敏注解
 * @date 2024-04-17 16:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize
@CustomConverter(IdCardConverterImpl.class)
public @interface IdCardConverter {

}
