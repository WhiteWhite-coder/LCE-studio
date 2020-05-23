package com.miaojie.dao.impl;

import com.miaojie.dao.UserDao;
import com.miaojie.domain.User;
import com.miaojie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 作者：吴淼杰
 * 注释：老天保佑，佛祖保佑，别出bug！
 *
 * 新学习：DBUtils框架
 * DbUtils类、QueryRunner类 、ResultSetHandler接口
 * 1.QueryRunner类简单化了SQL查询，它与ResultSetHandler组合在一起使用可以完成大部分的数据库操作，能够大大减少编码量。
 * 2.QueryRunner类默认的构造方法需要一个 javax.sql.DataSource 来作参数的构造方法
 * 3.QueryRunner类的主要方法
 *   1.public Object query(String sql, Object[] params, ResultSetHandler rsh) throws SQLException:
 *     执行一个查询操作，在这个查询中，对象数组中的每个元素值被用来作为查询语句的置换参数。
 *     该方法会自行处理 PreparedStatement 和 ResultSet 的创建和关闭。
 *     它不将数据库连接提供给方法，并且它是从提供给构造方法的数据源(DataSource) 或使用的setDataSource 方法中重新获得 Connection。
 *   2.public int update(Connection conn, String sql, Object[] params) throws SQLException:
 *     用来执行一个更新（插入、更新或删除）操作。
 * 4.ResultSetHandler接口
 *   1.BeanListHandler：
 *     将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
 */
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        String sql = "select * from tb_user";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询用户失败", e);
        }
    }
    @Override
    public User findById(Integer id) {
        String sql = "select * from tb_user where id=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanHandler<User>(User.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据id查询用户失败", e);
        }
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        String sql = "select * from tb_user where username=? and password=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanHandler<User>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据用户名和密码查询用户失败", e);
        }
    }

    @Override
    public User findByUserName(String username) {
        String sql = "select * from tb_user where username=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanHandler<User>(User.class),username);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据用户名查询用户失败", e);
        }
    }


    @Override
    public void add(User user) {
        String sql = "insert into tb_user(username,password,email,gender,flag,role,code) values(?,?,?,?,?,?,?)";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        Object[] params={user.getUsername(),user.getPassword(),user.getEmail(),
                user.getGender(),user.getFlag(),user.getRole(),user.getCode()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加用户失败", e);
        }
    }

    @Override
    public void updata(User user) {
        String sql = "update  tb_user set username=?,password=?,email=?,gender=?,flag=? where id=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        Object[] params={user.getUsername(),user.getPassword(),user.getEmail(),
                user.getGender(),user.getFlag(),user.getId()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更新用户失败", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from  tb_user  where id=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除用户失败", e);
        }
    }

    @Override
    public List<User> getUserList() {
        String sql = "select * from tb_user where flag=1";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取用户失败", e);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "update tb_user set flag=2 where id=?";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            int res = qr.update(sql,id);
            if(res > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除用户失败", e);
        }
        return false;
    }

    @Override
    public List<User> searchUser(String username, String gender) {
        String sql = "select * from tb_user where flag=1";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        if(!"".equals(username)){
            sql +=" and username like '%"+username+"%'";
        }
        if(!"".equals(gender)){
            sql +=" and gender='"+gender+"'";
        }
        try {
            List<User> list = qr.query(sql, new BeanListHandler<User>(User.class));
            if(list.size()>0){
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

