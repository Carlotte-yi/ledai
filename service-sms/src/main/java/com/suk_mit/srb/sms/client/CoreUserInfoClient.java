package com.suk_mit.srb.sms.client;

import com.suk_mit.common.result.R;
import com.suk_mit.srb.sms.client.fallback.CoreUserInfoClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author suk_mit
 * @Date 2021/10/31 3:37
 * @Version 1.0
 */
@FeignClient(value = "service-core",fallback = CoreUserInfoClientFallback.class)
public interface CoreUserInfoClient {

    @GetMapping("/api/core/userInfo/checkMobile/{mobile}")
    boolean checkMobile(@PathVariable("mobile") String mobile);
}
