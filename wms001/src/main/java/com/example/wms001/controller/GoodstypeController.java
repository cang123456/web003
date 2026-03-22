package com.example.wms001.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms001.common.QueryQageParam;
import com.example.wms001.common.Result;
import com.example.wms001.entity.Goodstype;
import com.example.wms001.entity.Goodstype;
import com.example.wms001.service.IGoodstypeService;
import com.example.wms001.service.IGoodstypeService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buyaola
 * @since 2026-03-22
 */
@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {
    @Autowired
    private IGoodstypeService goodstypeService;
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Goodstype goodstype) {
        return  goodstypeService.save(goodstype)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return  goodstypeService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Goodstype goodstype) {
        return  goodstypeService.updateById(goodstype)?Result.suc():Result.fail();
    }

    //查询 （模糊，匹配）
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryQageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("name");


        Page<Goodstype> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goodstype::getName,name);
        }

        IPage result = goodstypeService.pageCC(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

}
