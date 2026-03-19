package com.example.wms001.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wms001.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms001
 * @since 2026-02-02
 */
public interface IUserService extends IService<User> {

    IPage pageC(IPage<User> page);

    IPage pageCC(IPage<User> page, Wrapper lambdaQueryWrapper);
}
