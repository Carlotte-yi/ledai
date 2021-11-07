package com.suk_mit.srb.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author suk_mit
 * @Date 2021/9/20 17:30
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.suk_mit.srb","com.suk_mit.common"})
public class ServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class,args);
    }
}
