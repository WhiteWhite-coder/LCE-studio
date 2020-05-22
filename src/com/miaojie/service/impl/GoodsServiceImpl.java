package com.miaojie.service.impl;

import com.miaojie.dao.GoodsDao;
import com.miaojie.dao.GoodsTypeDao;
import com.miaojie.dao.impl.GoodsDaoImpl;
import com.miaojie.dao.impl.GoodsTypeDaoImpl;
import com.miaojie.domain.Goods;
import com.miaojie.domain.GoodsType;
import com.miaojie.domain.PageBean;
import com.miaojie.service.GoodsService;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public PageBean<Goods> findPageByWhere(int pageNum,int pageSize,String condition) {

        long totalSize = goodsDao.getCount(condition);
        List<Goods> data = goodsDao.findPageByWhere(pageNum,pageSize,condition);

        PageBean<Goods> pageBean = new PageBean<>(pageNum, pageSize, totalSize , data);

        return pageBean;
    }

    @Override
    public Goods findById(int gid) {
        Goods goods = goodsDao.findById(gid);//goodsType null
        //根据商品类型id，来查询商品类型
        GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
        GoodsType goodsType = goodsTypeDao.findById(goods.getTypeid());
        //将goodsType赋值给goods里的goodsType
        goods.setGoodsType(goodsType);
        return goods;
    }
}
