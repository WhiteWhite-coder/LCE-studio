package com.miaojie.dao.impl;

import com.miaojie.dao.AddressDao;
import com.miaojie.domain.Address;
import com.miaojie.domain.Cart;
import com.miaojie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class AddressDaoImpl implements AddressDao {
    @Override
    public List<Address> findByUid(int uid) {
        String sql = "select * from tb_address where uid=?";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<>(Address.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询订单失败", e);
        }
    }
}
