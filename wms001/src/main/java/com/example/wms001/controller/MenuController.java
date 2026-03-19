package com.example.wms001.controller;

import com.example.wms001.common.Result;
import com.example.wms001.entity.User;
import com.example.wms001.service.IMenuService;
import com.example.wms001.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.wms001.entity.Menu;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buyaola
 * @since 2026-03-19
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;


    @GetMapping("/list")
    public Result checkDuplicate(@RequestParam String roleId) {
//        System.out.println("no==="+no);
        List list = menuService.lambdaQuery().like(Menu::getMenuRight,roleId).list();
//        System.out.println(list.size());
        return list.size()>0?Result.suc(list):Result.fail();
    }

}
