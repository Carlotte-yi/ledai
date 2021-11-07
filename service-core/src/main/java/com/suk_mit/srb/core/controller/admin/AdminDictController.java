package com.suk_mit.srb.core.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.suk_mit.common.exception.BusinessException;
import com.suk_mit.common.result.R;
import com.suk_mit.common.result.ResponseEnum;
import com.suk_mit.srb.core.pojo.dto.ExcelDictDTO;
import com.suk_mit.srb.core.pojo.entity.Dict;
import com.suk_mit.srb.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2021-08-25
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
//@CrossOrigin
public class AdminDictController {

    @Resource
    DictService dictService;

    @ApiOperation("Excel数据批量导入")
    @PostMapping("/import")
    public R batchImport(
            @ApiParam(value = "Excel数据字典文件",required = true)
            @RequestParam("file")MultipartFile file
            ){
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            dictService.importData(inputStream);

            return R.ok().message("数据字典批量导入成功");
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }
    }

    @ApiOperation("Excel导出")
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet().doWrite(dictService.listDictData());
    }

    @ApiOperation("根据上级id获取子节点的数据列表")
    @GetMapping("/listbyParentId/{parentId}")
    public R listByParenId(@ApiParam(value = "上级节点id",required = true)
                           @PathVariable("parentId") Long parentId) {
        List<Dict> dictList = dictService.listByParentId(parentId);
        return R.ok().data("list",dictList);
    }

}

