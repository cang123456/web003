package com.example.wms001.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms001.entity.Goodstype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wms001.entity.Storage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author buyaola
 * @since 2026-03-22
 */
public interface IGoodstypeService extends IService<Goodstype> {

//    IPage pageCC(Page<Goodstype> page, Wrapper lambdaQueryWrapper);
    IPage pageCC(IPage<Goodstype> page, Wrapper lambdaQueryWrapper);

}
