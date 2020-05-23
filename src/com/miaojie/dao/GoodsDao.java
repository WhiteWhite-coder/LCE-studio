package com.miaojie.dao;

import com.miaojie.domain.Goods;
import com.miaojie.domain.GoodsType;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface GoodsDao {
    //查询商品个数
    long getCount(String condition);
    //根据条件分页查询
    List<Goods> findPageByWhere(int pageNum,int pageSize,String condition);
    //根据商品id查找
    Goods findById(int gid);
    //获取商品列表
    public List<Goods> getGoodsList();
    //获取商品分类列表
    public List<GoodsType> getGoodsTypeList();
    //添加新的商品分类
    public boolean addGoodsType(GoodsType gt);
    //添加商品
    public boolean addGoods(Goods goods);
    //根据id获取等级
    public Integer getGoodsTypeLevel(int id);
    //删除商品
    public boolean deleteGood(int id);
    //修改商品
    void updateGoods(Goods goods);
}
