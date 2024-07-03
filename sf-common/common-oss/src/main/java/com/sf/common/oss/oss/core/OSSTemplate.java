package com.sf.common.oss.oss.core;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.sf.common.oss.oss.autoconfigure.OSSProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.List;

/**
 * @author ky
 * @description
 * @date 2024-07-01 11:18
 */
@Getter
@AllArgsConstructor
public class OSSTemplate {

    private OSS ossClient;

    private OSSProperties ossProperties;

    /**
     * 创建存储空间
     *
     * @param bucketName
     */
    public void createBucket(String bucketName) {
        CreateBucketRequest request = new CreateBucketRequest(bucketName);
        ossClient.createBucket(request);
    }

    /**
     * 创建存储空间
     *
     * @param request
     */
    public void createBucket(CreateBucketRequest request) {
        ossClient.createBucket(request);
    }

    /**
     * 存储空间是否存在
     *
     * @param bucketName
     * @return
     */
    public Boolean doesBucketExist(String bucketName) {
        return ossClient.doesBucketExist(bucketName);
    }

    /**
     * 列举存储空间
     *
     * @return Bucket 集合
     */
    @SneakyThrows
    public List<Bucket> listBuckets() {
        return ossClient.listBuckets();
    }

    /**
     * 删除存储空间
     *
     * @param bucketName
     */
    public void deleteBucket(String bucketName) {
        ossClient.deleteBucket(bucketName);
    }

    /**
     * 简单上传
     *
     * @param bucketName
     * @param key
     * @param input
     * @return
     */
    public PutObjectResult putObject(String bucketName, String key, InputStream input) {
        PutObjectRequest request = new AppendObjectRequest(bucketName, key, input);
        return ossClient.putObject(request);
    }

    /**
     * 简单上传
     *
     * @param request
     * @return
     */
    public PutObjectResult putObject(PutObjectRequest request) {
        return ossClient.putObject(request);
    }

    /**
     * 简单下载
     *
     * @param bucketName
     * @param key
     */
    public InputStream getObject(String bucketName, String key) {
        GetObjectRequest request = new GetObjectRequest(bucketName, key);
        OSSObject object = ossClient.getObject(request);
        return object.getObjectContent();
    }

    /**
     * 简单下载
     *
     * @param request
     * @return
     */
    public InputStream getObject(GetObjectRequest request) {
        OSSObject object = ossClient.getObject(request);
        return object.getObjectContent();
    }

    /**
     * 查询存储对象是否存在
     *
     * @param bucketName
     * @param key
     * @return
     */
    public Boolean doesObjectExist(String bucketName, String key) {
        GenericRequest request = new GenericRequest(bucketName, key);
        return ossClient.doesObjectExist(request);
    }

    /**
     * 查询存储对象是否存在
     *
     * @param request
     * @return
     */
    public Boolean doesObjectExist(GenericRequest request) {
        return ossClient.doesObjectExist(request);
    }

    /**
     * 删除文件或目录（目录必须为空）
     *
     * @param bucketName
     * @param key
     */
    public void deleteObject(String bucketName, String key) {
        GenericRequest request = new GenericRequest(bucketName, key);
        ossClient.deleteObject(request);
    }

    /**
     * 删除文件或目录（目录必须为空）
     *
     * @param request
     */
    public void deleteObject(GenericRequest request) {
        ossClient.deleteObject(request);
    }
}
