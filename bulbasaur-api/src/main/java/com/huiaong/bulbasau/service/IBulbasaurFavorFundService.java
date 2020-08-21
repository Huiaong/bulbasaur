package com.huiaong.bulbasau.service;

import com.huiaong.bulbasau.entity.FavorFund;

import java.util.List;

public interface IBulbasaurFavorFundService {

    Boolean create(FavorFund favorFund);

    List<FavorFund> findByOpenId(String openId);

    Boolean favorFundExistByOpenIdAndFundCode(String openId, String text);
}
