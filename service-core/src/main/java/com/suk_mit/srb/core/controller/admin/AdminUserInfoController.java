package com.suk_mit.srb.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suk_mit.common.exception.Assert;
import com.suk_mit.common.result.R;
import com.suk_mit.common.result.ResponseEnum;
import com.suk_mit.common.utils.RegexValidateUtils;
import com.suk_mit.srb.base.utils.JwtUtils;
import com.suk_mit.srb.core.pojo.entity.UserInfo;
import com.suk_mit.srb.core.pojo.query.UserInfoQuery;
import com.suk_mit.srb.core.pojo.vo.LoginVO;
import com.suk_mit.srb.core.pojo.vo.RegisterVO;
import com.suk_mit.srb.core.pojo.vo.UserIndexVO;
import com.suk_mit.srb.core.pojo.vo.UserInfoVO;
import com.suk_mit.srb.core.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author XYF
 * @since 2021-05-06
 */


@Api(tags = "会员管理接口")
@RestController
@RequestMapping("/admin/core/userInfo")
@Slf4j
//@CrossOrigin
public class AdminUserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("获取会员分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码",required = true)
            @PathVariable("page") Long page,
            @ApiParam(value = "每页记录数",required = true)
            @PathVariable("limit") Long limit,
            @ApiParam(value = "查询对象",required = false)
            UserInfoQuery userInfoQuery
            ) {
        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> pageModel = userInfoService.listPage(pageParam,userInfoQuery);
        return R.ok().data("pageModel",pageModel);
    }
    @ApiOperation("锁定用户")
    @PutMapping("/lock/{id}/{status}")
    public R lock(
            @ApiParam(value = "用户id", required = true)
            @PathVariable("id") Long id,
            @ApiParam(value = "锁定状态(0：锁定 1：正常)",required = true)
            @PathVariable("status") Integer status
    ) {
        userInfoService.lock(id,status);
        return R.ok().message(status == 1?"解锁成功":"锁定成功");
    }
}