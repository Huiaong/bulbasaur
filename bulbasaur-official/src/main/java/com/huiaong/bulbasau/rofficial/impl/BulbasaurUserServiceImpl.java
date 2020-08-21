package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.entity.User;
import com.huiaong.bulbasau.rofficial.dao.IBulbasaurUserDao;
import com.huiaong.bulbasau.service.IBulbasaurUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulbasaurUserServiceImpl implements IBulbasaurUserService {

    @Autowired
    private IBulbasaurUserDao userDao;


    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Boolean userExistByOpenId(String openId) {
        return userDao.userExistByOpenId(openId) == 1;
    }

    @Override
    public Boolean create(User user) {
        return userDao.insert(user) >= 1;
    }

    @Override
    public Boolean deleteUserByOpenId(String userOpenId) {
        return userDao.deleteUserByOpenId(userOpenId) >= 1;
    }

    @Override
    public User findByOpenId(String openId) {
        return userDao.findByOpenId(openId);
    }
}
