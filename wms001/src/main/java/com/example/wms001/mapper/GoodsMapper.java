package com.example.wms001.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.wms001.entity.Goods;
import com.example.wms001.entity.Goodstype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author buyaola
 * @since 2026-03-24
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

//    @Select("select id from goods")
//    public List<Goods> list();

    IPage pageCC(IPage<Goods> page, @Param(Constants.WRAPPER) Wrapper<Goods> wrapper);
}
