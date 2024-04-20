package com.sf.common.base.util.desensitization.annotation.impl;


import com.sf.common.base.util.desensitization.base.BaseConverter;

/**
 * @author ky
 * @description 邮箱脱敏
 * @date 2024-04-17 16:40
 */
public class EmailConverterImpl extends BaseConverter<String, String> {

    @Override
    protected String doConvert(String value) {
        String encrypt = value.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
        if (value.equalsIgnoreCase(encrypt)) {
            encrypt = value.replaceAll("(\\w*)\\w{1}@(\\w+)", "$1*@$2");
        }
        return encrypt;
    }
}
