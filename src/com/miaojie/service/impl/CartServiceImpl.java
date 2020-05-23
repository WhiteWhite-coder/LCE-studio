package com.miaojie.service.impl;

import com.miaojie.dao.CartDao;
import com.miaojie.dao.impl.CartDaoImpl;
import com.miaojie.domain.Cart;
import com.miaojie.domain.Goods;
import com.miaojie.service.CartService;
import com.miaojie.service.GoodsService;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    @Override
    public Cart findByUidAndPid(int uid, int pid) {
        return cartDao.findByUidAndPid(uid, pid);
    }

    @Override
    public void add(Cart cart) {
        cartDao.add(cart);
    }

    @Override
    public void update(Cart cart) {
        cartDao.update(cart);
    }

    @Override
    public List<Cart> findByUid(int uid) {
        /**
         *         Goods goods = goodsDao.findById(gid);//goodsType null
         *         //根据商品类型id，来查询商品类型
         *         GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
         *         GoodsType goodsType = goodsTypeDao.findById(goods.getTypeid());
         *         //将goodsType赋值给goods里的goodsType
         *         goods.setGoodsType(goodsType);
         */
        List<Cart> carts = cartDao.findByUid(uid);
        //根据商品id，来查询商品
        if(carts != null){
            GoodsService goodsService = new GoodsServiceImpl();
            for (Cart cart : carts) {
                Goods goods = goodsService.findById(cart.getPid());
                //将good赋值给cart里的goods
                cart.setGoods(goods);
            }
        }
        return carts;
    }

    @Override
    public void delete(int uid, int pid) {
        cartDao.delete(uid, pid);
    }

    @Override
    public void deleteByUid(int uid) {
        cartDao.deleteByUid(uid);
    }
}
