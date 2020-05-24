package com.miaojie.service.impl;

import com.miaojie.dao.AddressDao;
import com.miaojie.dao.CartDao;
import com.miaojie.dao.impl.AddressDaoImpl;
import com.miaojie.dao.impl.CartDaoImpl;
import com.miaojie.domain.Address;
import com.miaojie.service.AddressService;

import java.util.List;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class AddressServiceImpl implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();
    @Override
    public List<Address> findByUid(int uid) {
        return addressDao.findByUid(uid);
    }

    @Override
    public void add(Address address) {
        addressDao.add(address);
    }

    @Override
    public void updateDefault(int aid, int uid) {
        addressDao.updateDefault(aid, uid);
    }
}
