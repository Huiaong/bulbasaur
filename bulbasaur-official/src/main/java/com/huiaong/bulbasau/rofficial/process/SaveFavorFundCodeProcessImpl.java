package com.huiaong.bulbasau.rofficial.process;

import cn.hutool.json.JSONUtil;
import com.huiaong.bulbasau.contains.FavorFundCodeContains;
import com.huiaong.bulbasau.entity.FavorFund;
import com.huiaong.bulbasau.entity.User;
import com.huiaong.bulbasau.process.IFavorFundCodeProcess;
import com.huiaong.bulbasau.service.IBulbasaurFavorFundService;
import com.huiaong.bulbasau.service.IBulbasaurUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component(FavorFundCodeContains.FAVOR_FUND_CODE_SAVE)
public class SaveFavorFundCodeProcessImpl implements IFavorFundCodeProcess {

    @Autowired
    private IBulbasaurUserService userService;
    @Autowired
    private IBulbasaurFavorFundService favorFundService;

    @Override
    public String processFundCode(String text, String openId) {
        log.info("Handling save fund code process, {} by {}", text, openId);

        Boolean favorFundExist = favorFundService.favorFundExistByOpenIdAndFundCode(openId, text);

        // 当前基金没收藏则继续收藏
        if (!favorFundExist) {

            User user = userService.findByOpenId(openId);

            FavorFund favorFund = new FavorFund();
            favorFund.setFundCode(text);
            favorFund.setUserId(user.getId());
            favorFund.setCreateAt(LocalDateTime.now());
            favorFund.setUpdateAt(LocalDateTime.now());
            favorFund.setDelFlag(1);

            Boolean createSuccess = favorFundService.create(favorFund);
            if (!createSuccess) {
                log.error("failed to create favor fund:{} by user:{}", text, JSONUtil.toJsonStr(user));
                return "\n当前基金收藏失败";
            } else {
                return "\n当前基金收藏成功";
            }
        } else {
            return "\n当前基金已收藏";
        }
    }
}
