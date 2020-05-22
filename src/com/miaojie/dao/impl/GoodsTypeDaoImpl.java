package com.miaojie.dao.impl;

import com.miaojie.dao.GoodsTypeDao;
import com.miaojie.domain.GoodsType;
import com.miaojie.domain.User;
import com.miaojie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class GoodsTypeDaoImpl implements GoodsTypeDao {
    @Override
    public List<GoodsType> findByLevel(int level) {
        String sql = "select * from tb_goods_type where level=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql,new BeanListHandler<>(GoodsType.class),level);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询商品类型失败", e);
        }
    }

    @Override
    public GoodsType findById(int typeid) {
        String sql = "select * from tb_goods_type where id=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql,new BeanHandler<>(GoodsType.class),typeid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据id查询商品类型失败", e);
        }
    }
}
