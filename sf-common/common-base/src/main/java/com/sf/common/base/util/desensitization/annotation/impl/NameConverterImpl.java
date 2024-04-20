package com.sf.common.base.util.desensitization.annotation.impl;

import com.sf.common.base.util.desensitization.base.BaseConverter;

/**
 * @author ky
 * @description 名称脱敏（隐藏第二位）
 * @date 2024-04-17 16:40
 */
public class NameConverterImpl extends BaseConverter<String, String> {

    @Override
    protected String doConvert(String value) {
        return value.replaceAll("(\\S)\\S(\\S*)", "$1*$2");
    }
}
