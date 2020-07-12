package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

/**
 * @author GodXuan
 * @date 2019/11/27 0027 - 下午 4:36
 */
public interface UserService {
    /*
    判断用户是否已经存在
     */
    boolean queryUsernameIsExist(String username);
    /*
    创建用户
     */
    Users createUser(UserBO userBO);
    /*
        用于登录，检索用户名密码是否匹配
         */
    Users queryUserForLogin(String username,String password);
}
