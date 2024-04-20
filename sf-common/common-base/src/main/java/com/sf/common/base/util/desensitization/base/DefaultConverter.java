package com.sf.common.base.util.desensitization.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * @author ky
 * @description 默认数据转换器
 * @date 2024-04-17 16:27
 */
public class DefaultConverter<T, D> extends JsonSerializer<T> implements ContextualSerializer {

    private BaseConverter<T, D> converter;

    @Override
    public void serialize(T value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(converter.doConvert(value));
    }

    /**
     * 设置转换器
     *
     * @param serializerProvider
     * @param beanProperty
     * @return
     * @throws JsonMappingException
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        CustomConverter annotation = beanProperty.getAnnotation(CustomConverter.class);
        if (Objects.nonNull(annotation)) {
            Class<? extends BaseConverter> w = annotation.value();
            try {
                this.converter = w.newInstance();
                this.converter.beanProperty = beanProperty;
            } catch (Exception ignore) {

            }
            return this;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }
}
