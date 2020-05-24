package com.miaojie.dao.impl;

import com.miaojie.dao.GoodsDao;
import com.miaojie.domain.Goods;
import com.miaojie.domain.GoodsType;
import com.miaojie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class GoodsDaoImpl implements GoodsDao {
    @Override
    public long getCount(String condition) {  //" typeId=1";
        String sql = "select count(*) from tb_goods";
        if(condition != null && condition.trim().length() != 0){
            sql = sql + " where "+condition;   // select count(*) from tb_goods  where  typeId=1
        }
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
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
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanHandler<>(Goods.class),gid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("商品查询失败", e);
        }
    }



    @Override
    public List<Goods> getGoodsList() {
        String sql = "select tb_goods_type.name as typeName,tb_goods.* " +
                "from tb_goods,tb_goods_type where tb_goods_type.id = tb_goods.typeid";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<Goods>(Goods.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取商品失败", e);
        }
    }

    @Override
    public List<GoodsType> getGoodsTypeList() {
        String sql = "select gta.*,gtb.name parentName from" +
                " tb_goods_type gta left join tb_goods_type gtb on gta.parent = gtb.id";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<GoodsType>(GoodsType.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取商品类型失败", e);
        }
    }

    @Override
    public boolean addGoodsType(GoodsType gt) {
        String sql = "insert into tb_goods_type(name,level,parent) values(?,?,?)";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            int res = qr.update(sql, gt.getName(),gt.getLevel(),gt.getParent());
            if(res>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加商品类型失败", e);
        }
        return false;
    }

    @Override
    public boolean addGoods(Goods goods) {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            String sql = "insert into tb_goods(name,picture,price,star,intro,typeid) values(?,?,?,?,?,?)";
            int res = qr.update(sql, goods.getName(),goods.getPicture(),goods.getPrice(),
                    goods.getStar(),goods.getIntro(),goods.getTypeid());
            if(res>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加商品失败", e);
        }
        return false;
    }

    @Override
    public Integer getGoodsTypeLevel(int id) {
        String sql = "select level from tb_goods_type where id=?";
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            return (Integer)qr.query(sql, new ScalarHandler("level"),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据id获取等级失败", e);
        }
    }

    @Override
    public boolean deleteGood(int id) {
        String sql = "delete from tb_goods where id=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            int res = qr.update(sql,id);
            if(res>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除商品失败", e);
        }
        return false;
    }

    @Override
    public void updateGoods(Goods goods) {
        String sql = "update tb_goods set name=?,price=?,star=?";
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
        try {
            qr.update(sql,goods.getName(),goods.getPrice(),goods.getStar());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更新商品失败", e);
        }
    }
}
