package com.huiaong.bulbasau.rofficial.process;

import cn.hutool.json.JSONUtil;
import com.huiaong.bulbasau.contains.FavorFundCodeContains;
import com.huiaong.bulbasau.entity.FavorFund;
import com.huiaong.bulbasau.process.IFavorFundCodeProcess;
import com.huiaong.bulbasau.service.IBulbasaurFavorFundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component(FavorFundCodeContains.FAVOR_FUND_CODE_DEL)
public class DelFavorFundCodeProcessImpl implements IFavorFundCodeProcess {

    @Autowired
    private IBulbasaurFavorFundService favorFundService;

    @Override
    public String processFundCode(String text, String openId) {
        log.info("Handling remove fund code process, {} by {}", text, openId);

        FavorFund favorFund = favorFundService.findByOpenIdAndFundCode(openId, text);

        // 当前基金已收藏则取消收藏
        if (Objects.nonNull(favorFund)) {
            Boolean deleteSuccess = favorFundService.deleteById(favorFund.getId());
            if (!deleteSuccess) {
                log.error("failed to delete favor fund:{}", JSONUtil.toJsonStr(favorFund));
                return "\n当前基金取消收藏失败";
            } else {
                return "\n当前基金取消收藏成功";
            }
        } else {
            return "\n当前基金未收藏";
        }
    }
}
