package com.miaojie.dao;

import com.miaojie.domain.Cart;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface CartDao {
    Cart findByUidAndPid(int uid, int pid);

    void add(Cart cart);

    void update(Cart cart);
}