package com.miaojie.service;

import com.miaojie.domain.Goods;
import com.miaojie.domain.GoodsType;
import com.miaojie.domain.PageBean;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface GoodsService {
    PageBean<Goods> findPageByWhere(int pageNum,int pageSize,String condition);

    Goods findById(int gid);

    //4.获取商品列表
    public List<Goods> getGoodsList();
    //1.获取商品分类列表
    public List<GoodsType> getGoodsTypeList();
    //3.添加新的商品分类
    public boolean addGoodsType(GoodsType gt);
    //5.添加商品
    public boolean addGoods(Goods goods);
    //2.根据id获取等级
    public Integer getGoodsTypeLevel(int id);
    //10000.
    boolean deleteGood(int id);

    void updateGoods(Goods goods);
}
