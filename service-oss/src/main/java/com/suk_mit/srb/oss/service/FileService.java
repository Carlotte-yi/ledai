package com.suk_mit.srb.oss.service;

import java.io.InputStream;

/**
 * @Author suk_mit
 * @Date 2021/9/21 17:29
 * @Version 1.0
 */
public interface FileService {
    String upload(InputStream inputStream, String module, String fileName);

    void removeFile(String url);
}
