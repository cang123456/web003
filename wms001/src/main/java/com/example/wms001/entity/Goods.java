package com.example.wms001.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author buyaola
 * @since 2026-03-24
 */
@Getter
@Setter
@ToString
@TableName("goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货名
     */
    @TableField("name")
    private String name;

    /**
     * 仓库
     */
    @TableField("storage")
    private Integer storage;

    /**
     * 分类
     */
    @TableField("goodsType")
    private Integer goodsType;

    /**
     * 数量
     */
    @TableField("count")
    private Integer count;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
