package com.miaojie.dao.impl;

import com.miaojie.dao.GoodsDao;
import com.miaojie.domain.Goods;
import com.miaojie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class GoodsDaoImpl implements GoodsDao {
    @Override
    public long getCount(String condition) {  //" typeId=1";
        String  sql="select count(*) from tb_goods";
        if(condition!=null&&condition.trim().length()!=0){
            sql=sql+" where "+condition;   // select count(*) from tb_goods  where  typeId=1
        }
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return (long) qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询商品个数失败", e);
        }

    }

    @Override
    public List<Goods> findPageByWhere(int pageNum,int pageSize,String condition) {
        String sql = "select * from tb_goods";
        if(condition != null && condition.trim().length() != 0){
            sql=sql+" where "+condition;   // select *  from tb_goods  where  typeId=1
        }
        sql+=" order by id limit ?,?";

        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql,new BeanListHandler<Goods>(Goods.class),(pageNum-1)*pageSize,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("分页查询失败", e);
        }
    }

    @Override
    public Goods findById(int gid) {
        String sql = "select * from tb_goods where id=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanHandler<>(Goods.class),gid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("商品查询失败", e);
        }
    }
}
