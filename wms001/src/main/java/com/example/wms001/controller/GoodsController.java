package com.example.wms001.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms001.common.QueryQageParam;
import com.example.wms001.common.Result;
import com.example.wms001.entity.Goods;
import com.example.wms001.service.IGoodsService;
import com.example.wms001.service.IGoodstypeService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buyaola
 * @since 2026-03-24
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        return  goodsService.save(goods)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return  goodsService.removeById(id)?Result.suc():Result.fail();
    }

    //修改
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
        return  goodsService.updateById(goods)?Result.suc():Result.fail();
    }

    //查询 （模糊，匹配）
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryQageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String goodstype = String.valueOf(param.get("goodstype"));
        String storage = String.valueOf(param.get("storage"));

        Page<Goods> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goods::getName,name);
        }
        if(StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)){
            lambdaQueryWrapper.eq(Goods::getGoodsType,goodstype);
        }
        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
            lambdaQueryWrapper.eq(Goods::getStorage,storage);
        }

        IPage result = goodsService.pageCC(page, lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

}
