package com.example.wms001.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wms001.entity.Storage;
import com.example.wms001.entity.Storage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author buyaola
 * @since 2026-03-21
 */
public interface IStorageService extends IService<Storage> {

    IPage pageCC(IPage<Storage> page, Wrapper lambdaQueryWrapper);
}
