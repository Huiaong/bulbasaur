package com.huiaong.bulbasau.rofficial.dao;

import com.huiaong.bulbasau.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IBulbasaurUserDao {
    User findById(Long id);

    Integer insert(User user);

    Integer userExistByOpenId(String openId);

    Integer deleteUserByOpenId(String openId);

    User findByOpenId(String openId);
}
