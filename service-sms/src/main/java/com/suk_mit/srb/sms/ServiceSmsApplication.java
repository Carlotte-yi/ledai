package com.suk_mit.srb.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author suk_mit
 * @Date 2021/9/16 19:20
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.suk_mit.srb","com.suk_mit.common"})
@EnableFeignClients
public class ServiceSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class,args);
    }
}
