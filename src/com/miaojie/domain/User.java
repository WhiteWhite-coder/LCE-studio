package com.miaojie.domain;

/*
 *  作者：吴淼杰
 *  注释：老天保佑，佛祖保佑，别出bug！
 *
    Create Table

    CREATE TABLE `tb_user` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `username` varchar(20) NOT NULL,
      `password` varchar(32) NOT NULL,
      `email` varchar(50) NOT NULL,
      `gender` varchar(50) NOT NULL,
      `flag` int(11) DEFAULT NULL,
      `role` int(11) DEFAULT NULL,
      `code` varchar(100) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8

 */

//User用户实体类
public class User {
    private Integer id;//id
    private String username;//用户名
    private String password;//密码
    private String email;//邮箱
    private String gender;//性别
    private Integer flag;//标志 0 没有激活 1 表示激活 2 无效删除
    private Integer role;//角色 0 管理员（后台）/店家 1普通用户
    private String code;//验证码

    public User(Integer id, String username, String password, String email, String gender, Integer flag, Integer role, String code) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.flag = flag;
        this.role = role;
        this.code = code;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", flag=" + flag +
                ", role=" + role +
                ", code='" + code + '\'' +
                '}';
    }
}
