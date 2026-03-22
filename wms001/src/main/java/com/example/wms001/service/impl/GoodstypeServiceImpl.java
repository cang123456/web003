package com.example.wms001.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms001.entity.Goodstype;
import com.example.wms001.entity.User;
import com.example.wms001.mapper.GoodstypeMapper;
import com.example.wms001.service.IGoodstypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author buyaola
 * @since 2026-03-22
 */
@Service
public class GoodstypeServiceImpl extends ServiceImpl<GoodstypeMapper, Goodstype> implements IGoodstypeService {

    @Autowired
    private GoodstypeMapper goodstypeMapper;

//    @Override
//    public IPage pageCC(IPage<Goodstype> page, Wrapper wrapper) {
//        return goodstypeMapper.pageCC(page,wrapper);
//    }

    @Override
    public IPage pageCC(IPage<Goodstype> page, Wrapper wrapper) {
        return goodstypeMapper.pageCC(page,wrapper);
    }

}
