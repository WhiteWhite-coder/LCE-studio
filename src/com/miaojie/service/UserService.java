package com.miaojie.service;

import com.miaojie.domain.User;

import java.util.List;

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

    //查询用户列表
    public List<User> getUserList();
    //删除用户信息
    public boolean deleteUser(int id);
    //条件查询用户列表
    public List<User> searchUser(String username,String gender);
}
