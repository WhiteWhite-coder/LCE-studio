package com.miaojie.dao.impl;

import com.miaojie.dao.CartDao;
import com.miaojie.domain.Cart;
import com.miaojie.domain.User;
import com.miaojie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class CartDaoImpl implements CartDao {
    @Override
    public Cart findByUidAndPid(int uid, int pid) {
        String sql = "select * from tb_cart where id=? and pid=?";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanHandler<>(Cart.class),uid,pid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询购物车失败", e);
        }
    }

    @Override
    public void add(Cart cart) {
        String sql = "insert into tb_cart (id,pid,num,money) values(?,?,?,?)";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        Object[] params={cart.getId(),cart.getPid(),cart.getNum(),cart.getMoney()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加购物车失败", e);
        }
    }

    @Override
    public void update(Cart cart) {
        String sql = "update tb_cart set num=?,money=? where id=? and pid=?";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        Object[] params={cart.getNum(),cart.getMoney(),cart.getId(),cart.getPid()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更新购物车失败", e);
        }
    }

    @Override
    public List<Cart> findByUid(int uid) {
        String sql = "select * from tb_cart where id=?";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<>(Cart.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询购物车失败", e);
        }
    }

    @Override
    public void delete(int uid, int pid) {
        String sql = "delete from tb_cart where id=? and pid=?";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            qr.update(sql,uid,pid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除购物车失败", e);
        }
    }

    @Override
    public void deleteByUid(int uid) {
        String sql = "delete from tb_cart where id=?";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            qr.update(sql,uid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("清空购物车失败", e);
        }
    }
}
