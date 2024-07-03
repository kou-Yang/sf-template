package com.sf.common.oss.oss.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ky
 * @description
 * @date 2024-07-01 11:10
 */
@Data
@ConfigurationProperties(prefix = "object.storage.oss")
public class OSSProperties {

    /**
     * 是否开启
     */
    Boolean enabled = false;

    /**
     * 访问域名
     */
    String endpoint;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 存储空间
     */
    String bucketName;
}
