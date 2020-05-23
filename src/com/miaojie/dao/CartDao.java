package com.miaojie.dao;

import com.miaojie.domain.Cart;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface CartDao {
    //根据用户id和商品id查询
    Cart findByUidAndPid(int uid, int pid);
    //添加购物车
    void add(Cart cart);
    //更新购物车
    void update(Cart cart);
    //根据用户id查询
    List<Cart> findByUid(int uid);
    //删除购物车
    void delete(int uid, int pid);
    //根据用户id删除
    void deleteByUid(int uid);
}
