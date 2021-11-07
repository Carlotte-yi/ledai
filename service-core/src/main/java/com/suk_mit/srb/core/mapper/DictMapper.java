package com.suk_mit.srb.core.mapper;

import com.suk_mit.srb.core.pojo.dto.ExcelDictDTO;
import com.suk_mit.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2021-08-25
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDTO> list);
}
