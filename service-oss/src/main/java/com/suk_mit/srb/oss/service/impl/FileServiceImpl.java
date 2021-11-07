package com.suk_mit.srb.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.suk_mit.srb.oss.service.FileService;
import com.suk_mit.srb.oss.util.OssProperties;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Author suk_mit
 * @Date 2021/9/21 17:29
 * @Version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(InputStream inputStream, String module, String fileName) {
        String endpoint = OssProperties.ENDPOINT;
        String accessKeyId = OssProperties.KEY_ID;
        String accessKeySecret = OssProperties.KEY_SECRET;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(OssProperties.BUCKET_NAME)) {
            ossClient.createBucket(OssProperties.BUCKET_NAME);
            ossClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }
        //文件目录结构
        String timeFolder = new DateTime().toString("/yyyy/MM/dd/");
        String end = fileName.substring(fileName.lastIndexOf("."));
        String key = module  + timeFolder + UUID.randomUUID() + end;
        ossClient.putObject(OssProperties.BUCKET_NAME, key, inputStream);
        ossClient.shutdown();
        //https://srb-file-2.oss-cn-beijing.aliyuncs.com/avatar/6a27e7d903d7ebea586f7634ce22668b_4537750208068446236.jpg
        return "https://" + OssProperties.BUCKET_NAME + "." +OssProperties.ENDPOINT + "/" + key;
    }

    @Override
    public void removeFile(String url) {
        String endpoint = OssProperties.ENDPOINT;
        String accessKeyId = OssProperties.KEY_ID;
        String accessKeySecret = OssProperties.KEY_SECRET;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String host = "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/";
        String objectName = url.substring(host.length());

        ossClient.deleteObject(OssProperties.BUCKET_NAME, objectName);
    }
}
