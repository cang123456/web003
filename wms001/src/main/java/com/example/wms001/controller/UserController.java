package com.example.wms001.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms001.common.JwtUtil;
import com.example.wms001.common.QueryQageParam;
import com.example.wms001.common.Result;
import com.example.wms001.entity.User;
import com.example.wms001.entity.Menu;
import com.example.wms001.service.IMenuService;
import com.example.wms001.service.IUserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms001
 * @since 2026-02-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private  com.example.wms001.common.JwtUtil jwtUtil;
    @GetMapping("/list")
    public List<User> list() {
        return  userService.list();
    }



    // 文件上传和下载 multipartfile byte数组获取比特流
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            byte[] bytes = file.getBytes();
            String uploadDir = "D:\\project\\wms\\web002-main\\wms001\\file_upload_download\\";
            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);
            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }
    // 上传/下载的根目录（建议和上传接口保持一致，可从配置文件注入）
    private static final String UPLOAD_DIR = "D:\\project\\wms\\web002-main\\wms001\\file_upload_download\\";
    /**
     * 文件下载接口
     * @param fileName 要下载的文件名（前端传入，需和上传时的文件名一致）
     * @return 响应体（包含文件流）
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileName") String fileName) {
        // 1. 构建要下载的文件完整路径
        File downloadFile = new File(UPLOAD_DIR, fileName);

        // 2. 校验文件是否存在
        if (!downloadFile.exists()) {
            // 返回 404 状态码 + 提示信息
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(("文件不存在：" + fileName .getBytes(StandardCharsets.UTF_8)).getBytes());
        }

        // 3. 读取文件字节流
        try (InputStream inputStream = new FileInputStream(downloadFile)) {
            // 读取文件到字节数组
            byte[] fileBytes = new byte[(int) downloadFile.length()];
            inputStream.read(fileBytes);

            // 4. 设置响应头（关键：处理中文文件名、指定下载类型）
            HttpHeaders headers = new HttpHeaders();
            // 处理中文文件名乱码（URLEncoder 编码）
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
            // 设置响应头：指定文件下载方式、文件名
            headers.add("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
            // 设置响应媒体类型（二进制流，支持所有文件类型）
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 设置文件大小
            headers.setContentLength(fileBytes.length);

            // 5. 返回响应（200 成功 + 响应头 + 文件字节流）
            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            // 返回 500 状态码 + 错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("文件读取失败：" + e.getMessage()).getBytes(StandardCharsets.UTF_8));
        }
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return  userService.save(user)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return  userService.updateById(user)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user) {
        return  userService.updateById(user);
    }
    //新增或者修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user) {
        return  userService.saveOrUpdate(user);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(Integer id) {
        return  userService.removeById(id)?Result.suc():Result.fail();
    }
    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List list = userService.lambdaQuery()
                .eq(User::getNo,user.getNo())
                .eq(User::getPassword,user.getPassword())
                .list();
        if(list.size()>0){
            User user1 =  (User)list.get(0);
            List menuList = menuService.lambdaQuery()
                    .like(Menu::getMenuRight,user1.getRoleId())
                    .list();
            HashMap res = new HashMap();
            res.put("user",user1);

            String token = jwtUtil.createToken(user1.getId().toString(), null);
            res.put("token", token);

            res.put("menuList",menuList);
            return Result.suc(res);
        }
        return Result.fail();
    }
    //查询 （模糊，匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(user.getName())){
            queryWrapper.like(User::getName, user.getName());
        }
//        queryWrapper.eq(User::getName, user.getName());
        return Result.suc(userService.list(queryWrapper));
    }
    @GetMapping("/findByNo")
    public Result checkDuplicate(@RequestParam String no) {
//        System.out.println("no==="+no);
        List list = userService.lambdaQuery().eq(User::getNo,no).list();
//        System.out.println(list.size());
        return list.size()>0?Result.suc(list):Result.fail();
    }

    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryQageParam query) {
        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());
        HashMap param = query.getParam();

        String name = param.get("name").toString();
//        System.out.println("name==="+param.get("name"));
//        System.out.println("no==="+param.get("no"));
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(User::getName, user.getName());
////        queryWrapper.eq(User::getName, user.getName());
//        return userService.list(queryWrapper);
        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,name);

        IPage result = userService.page(page, lambdaQueryWrapper);

        System.out.println("total==="+result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryQageParam query) {
//        System.out.println("num==="+query.getPageNum());
//        System.out.println("size==="+query.getPageSize());
        HashMap param = query.getParam();
        String name = param.get("name").toString();

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

//        IPage result = userService.pageC(page);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,name);
        IPage result = userService.pageCC(page, lambdaQueryWrapper);

        System.out.println("total==="+result.getTotal());

        return result.getRecords();
    }
    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryQageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String sex = (String)param.get("sex");
        String roleId = (String)param.get("roleId");

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if(StringUtils.isNotBlank(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }

        IPage result = userService.pageCC(page, lambdaQueryWrapper);

//        System.out.println("total==="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }

}
