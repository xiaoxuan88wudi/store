package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author GodXuan
 * @date 2019/11/27 0027 - 下午 4:44
 */

@Api(value = "注册登录",tags = {"用于注册登录的接口"})
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    public UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){
        //判断是否为空
        if (StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        //判断是否重复
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist){
            return IMOOCJSONResult.errorMsg("用户名不能重复");
        }
        //请求成功，没有重复。
        return IMOOCJSONResult.ok();
    }
    @ApiOperation(value = "用户名注册",notes = "用户名注册",httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)
        ||StringUtils.isBlank(confirmPwd)){
            return IMOOCJSONResult.errorMsg("用户名和密码不能为空");
        }

        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }

        if(password.length()<6){
            return IMOOCJSONResult.errorMsg("用户密码不能小于6");
        }

        if (!password.equals(confirmPwd)){
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }

        Users res = userService.createUser(userBO);
        res = setNullProperty(res);

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(res),true);

        return IMOOCJSONResult.ok();
    }
    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();


        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return IMOOCJSONResult.errorMsg("用户名和密码不能为空");
        }



        Users res = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));



        if (res == null){
            return IMOOCJSONResult.errorMsg("用户名和密码不正确");
        }

        res = setNullProperty(res);

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(res),true);



        return IMOOCJSONResult.ok(res);
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        // 清除用户的相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");

        // TODO 用户退出登录，需要清空购物车
        // TODO 分布式会话中需要清除用户数据

        return IMOOCJSONResult.ok();
    }

}
