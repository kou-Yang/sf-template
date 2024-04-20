package com.sf.common.base.util.desensitization.annotation.impl;


import com.sf.common.base.util.desensitization.base.BaseConverter;

/**
 * @author ky
 * @description 手机号脱敏
 * @date 2024-04-17 16:40
 */
public class PhoneConverterImpl extends BaseConverter<String, String> {

    @Override
    protected String doConvert(String value) {
        return value.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
