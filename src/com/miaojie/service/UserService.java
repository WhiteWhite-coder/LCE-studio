package com.miaojie.service;

import com.miaojie.domain.User;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 *
 */
public interface UserService {
    /**
     *  注册
     */
    void register(User user);
    /**
     *  检查用户名是否存在
     */
    User checkUserName(String username);
    /**
     *  登录
     */
    User login(String username,String password);
}
