package com.example.wms001.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.wms001.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wms001.entity.Goodstype;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author buyaola
 * @since 2026-03-24
 */
@Service
public interface IGoodsService extends IService<Goods> {

    IPage pageCC(IPage<Goods> page, Wrapper lambdaQueryWrapper);
}
