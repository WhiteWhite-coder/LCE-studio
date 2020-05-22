package com.miaojie.dao;

import com.miaojie.domain.Goods;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface GoodsDao {
    long getCount(String condition);

    List<Goods> findPageByWhere(int pageNum,int pageSize,String condition);
}
