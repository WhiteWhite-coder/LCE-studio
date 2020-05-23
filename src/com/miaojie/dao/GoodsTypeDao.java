package com.miaojie.dao;

import com.miaojie.domain.GoodsType;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface GoodsTypeDao {
    //查询商品类型
    List<GoodsType> findByLevel(int level);
    //根据id查询商品类型
    GoodsType findById(int typeid);
}
