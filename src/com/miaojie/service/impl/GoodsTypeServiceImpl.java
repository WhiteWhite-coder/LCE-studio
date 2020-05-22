package com.miaojie.service.impl;

import com.miaojie.dao.GoodsTypeDao;
import com.miaojie.dao.UserDao;
import com.miaojie.dao.impl.GoodsTypeDaoImpl;
import com.miaojie.dao.impl.UserDaoImpl;
import com.miaojie.domain.GoodsType;
import com.miaojie.service.GoodsTypeService;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class GoodsTypeServiceImpl implements GoodsTypeService {
    GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
    @Override
    public List<GoodsType> getTypeByLevel(int level) {
        return goodsTypeDao.findByLevel(level);
    }
}
