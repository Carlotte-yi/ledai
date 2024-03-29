package com.suk_mit.srb.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suk_mit.srb.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk_mit.srb.core.pojo.query.UserInfoQuery;
import com.suk_mit.srb.core.pojo.vo.LoginVO;
import com.suk_mit.srb.core.pojo.vo.RegisterVO;
import com.suk_mit.srb.core.pojo.vo.UserIndexVO;
import com.suk_mit.srb.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);

    boolean checkMobile(String mobile);

    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    void lock(Long id, Integer status);

    UserIndexVO getIndexUserInfo(Long userId);
}
