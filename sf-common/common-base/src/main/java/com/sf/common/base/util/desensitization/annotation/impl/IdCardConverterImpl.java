package com.sf.common.base.util.desensitization.annotation.impl;

import com.sf.common.base.util.desensitization.base.BaseConverter;

/**
 * @author ky
 * @description 身份证脱敏（保留前、后四位）
 * @date 2024-04-17 16:40
 */
public class IdCardConverterImpl extends BaseConverter<String, String> {

    @Override
    protected String doConvert(String value) {
        return value.replaceAll("(\\d{6})\\d*([0-9a-zA-Z]{4})", "$1******$2");
    }
}
