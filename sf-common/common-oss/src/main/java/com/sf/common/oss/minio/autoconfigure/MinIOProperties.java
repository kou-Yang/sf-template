package com.sf.common.oss.minio.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ky
 * @description
 * @date 2023-12-15 10:05
 */
@Data
@ConfigurationProperties(prefix = "object.storage.minio")
public class MinIOProperties {

    /**
     * 是否开启
     */
    Boolean enabled = false;

    /**
     * 访问域名
     */
    String endpoint;

    /**
     * accessKey
     */
    String accessKey;

    /**
     * secretKey
     */
    String secretKey;

    /**
     * 存储空间
     */
    String bucketName;
}
