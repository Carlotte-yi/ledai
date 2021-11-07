package com.suk_mit.srb.sms.controller.api;

import com.suk_mit.common.exception.Assert;
import com.suk_mit.common.result.R;
import com.suk_mit.common.result.ResponseEnum;
import com.suk_mit.common.utils.RandomUtils;
import com.suk_mit.common.utils.RegexValidateUtils;
import com.suk_mit.srb.sms.client.CoreUserInfoClient;
import com.suk_mit.srb.sms.service.SmsService;
import com.suk_mit.srb.sms.util.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.CoreSubscriber;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author suk_mit
 * @Date 2021/9/19 22:31
 * @Version 1.0
 */
//@CrossOrigin
@RestController
@RequestMapping("/api/sms")
@Slf4j
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient client;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public R send(
            @ApiParam(value = "手机号", required = true)
            @PathVariable String mobile) {
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile),ResponseEnum.MOBILE_ERROR);
        //校验验证码
        boolean result = client.checkMobile(mobile);
        log.info("result = " + result);
        Assert.isTrue(result == false,ResponseEnum.MOBILE_EXIST_ERROR);
        Map<String, Object> map = new HashMap<>(1);
        map.put("code", RandomUtils.getFourBitRandom());
        redisTemplate.opsForValue().set("srb:sms:code"+mobile, map.get("code"));



//        smsService.send(mobile, SmsProperties.TEMPLATE_CODE,map);
        System.out.println(map.get("code"));
        return R.ok().message("短信发送成功");
    }
}
