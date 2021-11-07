package com.suk_mit.srb.core.service;

import com.suk_mit.srb.core.pojo.bo.TransFlowBO;
import com.suk_mit.srb.core.pojo.entity.TransFlow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author Jiangw
 * @since 2021-03-31
 */
public interface TransFlowService extends IService<TransFlow> {

    void saveTransFlow(TransFlowBO investTransFlowBO);

    boolean isSaveTransFlow(String agentBillNo);

    List<TransFlow> selectByUserId(Long userId);
}
