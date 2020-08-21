package com.huiaong.bulbasau.rofficial.dao;

import com.huiaong.bulbasau.entity.FavorFund;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBulbasaurFavorFundDao {

    Integer insert(FavorFund favorFund);

    Integer favorFundExistByOpenIdAndFundCode(@Param("openId") String openId, @Param("fundCode") String fundCode);

    Integer deleteFavorFundByOpenId(String openId);

    List<FavorFund> findByOpenId(String openId);
}
