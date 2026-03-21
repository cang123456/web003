package com.example.wms001.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.wms001.entity.Storage;
import com.example.wms001.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author buyaola
 * @since 2026-03-21
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

    IPage pageCC(IPage<Storage> page, @Param(Constants.WRAPPER) Wrapper<Storage> wrapper);
}
