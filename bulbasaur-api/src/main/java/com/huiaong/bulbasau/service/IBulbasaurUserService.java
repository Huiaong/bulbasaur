package com.huiaong.bulbasau.service;

import com.huiaong.bulbasau.entity.User;

public interface IBulbasaurUserService {

    User findById(Long id);

    Boolean userExistByOpenId(String openId);

    Boolean create(User user);

    Boolean deleteUserByOpenId(String userOpenId);

    User findByOpenId(String openId);
}
