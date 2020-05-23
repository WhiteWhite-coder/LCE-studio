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

    //后台
    //1.管理员登录
    public User findAdmin(String username);
    //2.查询用户列表
    public List<User> getUserList();
    //3.删除用户信息
    public boolean deleteUser(int id);
    //4.条件查询用户列表
    public List<User> searchUser(String username,String gender);
}
