package com.miaojie.service;

import com.miaojie.domain.Goods;
import com.miaojie.domain.PageBean;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface GoodsService {
    PageBean<Goods> findPageByWhere(int pageNum,int pageSize,String condition);

    Goods findById(int gid);
}
