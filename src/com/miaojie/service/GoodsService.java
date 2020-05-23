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

    public List<Goods> getGoodsList();

    public List<GoodsType> getGoodsTypeList();

    public boolean addGoodsType(GoodsType gt);

    public boolean addGoods(Goods goods);

    public Integer getGoodsTypeLevel(int id);

    boolean deleteGood(int id);

    void updateGoods(Goods goods);
}
