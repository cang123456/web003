package com.example.wms001.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms001.common.QueryQageParam;
import com.example.wms001.common.Result;
import com.example.wms001.entity.Storage;
import com.example.wms001.entity.Storage;
import com.example.wms001.service.IStorageService;
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
 * @since 2026-03-21
 */
@RestController
@RequestMapping("/storage")
public class StorageController {


    @Autowired
    private IStorageService storageService;
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return  storageService.save(storage)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return  storageService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        return  storageService.updateById(storage)?Result.suc():Result.fail();
    }

    //查询 （模糊，匹配）
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryQageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("name");


        Page<Storage> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Storage::getName,name);
        }

        IPage result = storageService.pageCC(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }


}
