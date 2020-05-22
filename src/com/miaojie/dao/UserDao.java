package com.miaojie.dao;

import com.miaojie.domain.User;

import java.util.List;

/**
 *  作者：吴淼杰
 *  注释：老天保佑，佛祖保佑，别出bug！
 */
public interface UserDao {
    /**
     * 查询
     */
    List<User> findAll();
    /**
     * 根据用户id查询
     */
    User findById(Integer id);
    /**
     * 登录时候根据输入用户名字用户密码查询
     */
    User findByUserNameAndPassword(String username,String password);
    /**
     * 登录时候根据输入用户名字查询
     */
    User findByUserName(String username);
    /**
     * 注册用户
     */
    void add(User user);
    /**
     * 更新用户
     */
    void updata(User user);
    /**
     * 删除用户
     */
    void delete(Integer id);
}
