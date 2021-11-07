package com.suk_mit.srb.sms.client.fallback;

import com.suk_mit.srb.sms.client.CoreUserInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author suk_mit
 * @Date 2021/10/31 10:15
 * @Version 1.0
 */
@Service
@Slf4j
public class CoreUserInfoClientFallback implements CoreUserInfoClient {
    @Override
    public boolean checkMobile(String mobile) {
        log.error("远程调用失败,服务熔断");
        return false;
    }
}
