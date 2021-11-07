package com.suk_mit.srb.core.controller.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.suk_mit.srb.base.utils.JwtUtils;
import com.suk_mit.common.result.R;
import com.suk_mit.srb.core.pojo.entity.TransFlow;
import com.suk_mit.srb.core.service.TransFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 交易流水表 前端控制器
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
@Api(tags = "资金记录")
@RestController
@RequestMapping("/api/core/transFlow")
@Slf4j
public class TransFlowController {

    @Resource
    private TransFlowService transFlowService;

    @ApiOperation("获取列表")
    @GetMapping("/list")
    public R list(HttpServletRequest request) {
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        List<TransFlow> list = transFlowService.selectByUserId(userId);
        return R.ok().data("list", list);
    }
}

