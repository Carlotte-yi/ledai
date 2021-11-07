package com.suk_mit.srb.core.controller.admin;



import com.suk_mit.common.exception.BusinessException;
import com.suk_mit.common.result.R;
import com.suk_mit.common.result.ResponseEnum;
import com.suk_mit.srb.core.pojo.entity.IntegralGrade;
import com.suk_mit.srb.core.service.IntegralGradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author suk_mit
 * @Date 2021/8/25 15:09
 * @Version 1.0
 */
//@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {
    @Resource
    private IntegralGradeService integralGradeService;

    /**
     * 积分列表的获取
     *
     * @return
     */
    @GetMapping("/list")
    public R listAll() {
        final List<IntegralGrade> list = integralGradeService.list();
        return R.ok().data("list", list).message("获取列表成功");
    }

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    public R removeById(@PathVariable Long id) {
        final boolean result = integralGradeService.removeById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade) {
        if (integralGrade.getBorrowAmount() == null) {
            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        }
        boolean result = integralGradeService.save(integralGrade);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id获取积分")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id",required = true,example = "1")
            @PathVariable Long id) {
        IntegralGrade result = integralGradeService.getById(id);
        if (result != null) {
            return R.ok().data("record",result);
        } else {
            return R.error().message("获取数据失败");
        }
    }

    @ApiOperation("更新积分等级")
    @PutMapping("update")
    public R updateById(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade) {
        boolean result = integralGradeService.updateById(integralGrade);
        if (result) {
            return R.ok().message("更新成功");
        } else {
            return R.error().message("更新失败");
        }
    }
}
