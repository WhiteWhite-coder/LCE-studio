package com.miaojie.service;

import com.miaojie.domain.Address;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public interface AddressService {
    List<Address> findByUid(int uid);
}
