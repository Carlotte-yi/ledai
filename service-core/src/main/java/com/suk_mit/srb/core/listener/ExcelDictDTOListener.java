package com.suk_mit.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.suk_mit.srb.core.mapper.DictMapper;
import com.suk_mit.srb.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntFunction;

/**
 * @Author suk_mit
 * @Date 2021/9/10 20:03
 * @Version 1.0
 */
@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {

    List<ExcelDictDTO> list = new LinkedList<ExcelDictDTO>();

    public static final int BATCH_COUNT = 5;

    private DictMapper dictMapper;

    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条记录：{}",excelDictDTO);
        list.add(excelDictDTO);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    private void saveData() {
        log.info("{} 条数据被存储到数据库。。。。。。。。。",list.size());
        dictMapper.insertBatch(list);
        log.info("{} 条数据被存储到数据库成功!");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成! ");
    }
}
