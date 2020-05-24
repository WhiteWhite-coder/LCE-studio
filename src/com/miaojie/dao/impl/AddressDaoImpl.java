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
            return qr.query(sql, new BeanListHandler<Address>(Address.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询地址失败", e);
        }
    }

    @Override
    public void add(Address address) {
        String sql = "insert into tb_address(detail,name,phone,uid,level) values(?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        Object[] params={address.getDetail(),address.getName(),address.getPhone(),
                address.getUid(),address.getLevel()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加地址失败", e);
        }
    }

    @Override
    public void updateDefault(int aid, int uid) {
        String sql1 = "update tb_address set levle=0 where uid=?";//把当前用户的所有地址级别设为0
        String sql2 = "update tb_address set levle=1 where id=?";//把当前用户的aid地址级别设为1
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            qr.update(sql1,uid);
            qr.update(sql2,aid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改地址级别失败", e);
        }
    }

    @Override
    public void delete(int aid) {
        String sql = "delete from tb_address where id=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            qr.update(sql,aid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除地址失败", e);
        }
    }
}
