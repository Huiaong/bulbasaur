package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.entity.FavorFund;
import com.huiaong.bulbasau.rofficial.dao.IBulbasaurFavorFundDao;
import com.huiaong.bulbasau.service.IBulbasaurFavorFundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BulbasaurFavorFundServiceImpl implements IBulbasaurFavorFundService {

    @Autowired
    private IBulbasaurFavorFundDao favorFundDao;

    @Override
    public Boolean create(FavorFund favorFund) {
        return favorFundDao.insert(favorFund) > 0;
    }

    @Override
    public List<FavorFund> findByOpenId(String openId) {
        return favorFundDao.findByOpenId(openId);
    }

    @Override
    public Boolean favorFundExistByOpenIdAndFundCode(String openId, String text) {
        return favorFundDao.favorFundExistByOpenIdAndFundCode(openId, text) > 0;
    }
}
