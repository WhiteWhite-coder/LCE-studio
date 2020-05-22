package com.miaojie.service.impl;

import com.miaojie.dao.UserDao;
import com.miaojie.dao.impl.UserDaoImpl;
import com.miaojie.domain.User;
import com.miaojie.service.UserService;
import com.miaojie.utils.EmailUtils;
import com.miaojie.utils.Md5Utils;

/**
 *  作者：吴淼杰
 *  注释：老天保佑，佛祖保佑，别出bug！
 *
 */
public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    @Override
    public void register(User user) {
        //密码加密
        user.setPassword(Md5Utils.md5(user.getPassword()));
        //添加数据
        userDao.add(user);
        //发送邮件
        EmailUtils.sendEmail(user);
    }

    @Override
    public User checkUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User login(String username, String password) {
        //把密码加密后再比对
        password=Md5Utils.md5(password);
        return userDao.findByUserNameAndPassword(username, password);
    }
}
