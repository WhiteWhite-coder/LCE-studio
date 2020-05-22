package com.miaojie.service.impl;

import com.miaojie.dao.CartDao;
import com.miaojie.dao.impl.CartDaoImpl;
import com.miaojie.domain.Cart;
import com.miaojie.service.CartService;

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
}
