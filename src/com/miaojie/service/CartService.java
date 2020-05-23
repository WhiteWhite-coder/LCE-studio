package com.miaojie.service;

import com.miaojie.domain.Cart;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface CartService {
    Cart findByUidAndPid(int uid, int pid);

    void add(Cart cart);

    void update(Cart cart);

    List<Cart> findByUid(int uid);
}
