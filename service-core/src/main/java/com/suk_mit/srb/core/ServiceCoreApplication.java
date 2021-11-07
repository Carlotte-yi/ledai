package com.suk_mit.srb.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author suk_mit
 * @Date 2021/8/25 15:03
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.suk_mit.srb","com.suk_mit.common"})
public class ServiceCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class,args);
    }
}
