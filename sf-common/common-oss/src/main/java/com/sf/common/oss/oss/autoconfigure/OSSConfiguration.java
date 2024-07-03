package com.sf.common.oss.oss.autoconfigure;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sf.common.oss.oss.core.OSSTemplate;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ky
 * @description
 * @date 2024-07-01 11:14
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OSSProperties.class)
@ConditionalOnProperty(prefix = "object.storage.oss", name = "enabled", havingValue = "true")
public class OSSConfiguration {

    @Bean
    @SneakyThrows
    @ConditionalOnMissingBean(OSS.class)
    public OSS ossClient(OSSProperties ossProperties) {
        return new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
    }

    @Bean
    @ConditionalOnBean({OSS.class})
    @ConditionalOnMissingBean(OSSTemplate.class)
    public OSSTemplate ossTemplate(OSS ossClient, OSSProperties ossProperties) {
        return new OSSTemplate(ossClient, ossProperties);
    }
}