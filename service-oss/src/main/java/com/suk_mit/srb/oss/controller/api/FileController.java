package com.suk_mit.srb.oss.controller.api;

import com.suk_mit.common.exception.BusinessException;
import com.suk_mit.common.result.R;
import com.suk_mit.common.result.ResponseEnum;
import com.suk_mit.srb.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author suk_mit
 * @Date 2021/9/27 13:05
 * @Version 1.0
 */
@Api(tags = "阿里云文件管理")
@RequestMapping("/api/oss/file")
//@CrossOrigin
@RestController
public class FileController {
    @Resource
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(
            @ApiParam(value = "文件",required = true)
            @RequestParam("file")MultipartFile file,

            @ApiParam(value = "模块",required = true)
            @RequestParam("module") String module
            ) {
        try {
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            String url = fileService.upload(inputStream, module, filename);
            return R.ok().message("文件上传成功").data("url",url);
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }
    }

    @ApiOperation("文件删除")
    @DeleteMapping("/remove")
    public R removeFile(
            @ApiParam("文件url")
            @RequestParam("url") String url) {
        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }

}
