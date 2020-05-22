package com.miaojie.dao;

import com.miaojie.domain.GoodsType;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface GoodsTypeDao {
    List<GoodsType> findByLevel(int level);

    GoodsType findById(int typeid);
}
