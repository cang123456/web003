package com.example.wms001.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author buyaola
 * @since 2026-04-04
 */
@Data
@ToString
@TableName("record")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货品id
     */
    @TableField("goods")
    private Integer goods;

    /**
     * 取货人/补货人
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 操作人id
     */
    @TableField("admin_id")
    private Integer adminId;

    /**
     * 数量
     */
    @TableField("count")
    private Integer count;

    /**
     * 操作时间
     */
    @TableField("createtime")
    private LocalDateTime createtime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
