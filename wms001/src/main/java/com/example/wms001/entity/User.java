package com.example.wms001.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wms001
 * @since 2026-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String no;

    /**
     * 名字
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer age;

    /**
     * 电话
     */
    private Integer sex;

    /**
     * 角色 0超级管理员,1管理员,2普通账号
     */
    private String phone;

    /**
     * 是否有效,Y有效,其他无效
     */
    private Integer roleId;

    @TableField("isValid")
    private String isvalid;


}
