package com.huiaong.bulbasau.rofficial.process;

import com.huiaong.bulbasau.contains.FavorFundCodeContains;
import com.huiaong.bulbasau.contains.FundCodeContains;
import com.huiaong.bulbasau.process.IFavorFundCodeProcess;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component(FavorFundCodeContains.FAVOR_FUND_CODE_ADD)
public class AddFavorFundCodeProcessImpl implements IFavorFundCodeProcess {

    @Autowired
    @Qualifier(FavorFundCodeContains.FAVOR_FUND_CODE_PRINT)
    private IFavorFundCodeProcess printFundCodeProcess;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public String processFundCode(String text, String fromUserName) {
        log.info("Handling collect fund code process, {} by {}", text, fromUserName);

        StringBuilder sb = new StringBuilder();
        RBucket<String> favorFundBucket = redissonClient.getBucket(FundCodeContains.OPTION_FAVOR_FUND_REDIS_KEY + fromUserName);

        favorFundBucket.set(FavorFundCodeContains.FAVOR_FUND_CODE_ADD, 5, TimeUnit.MINUTES);
        sb.append("开始收藏5分钟内输入的基金");

        if (REUtil.matches("^\\+\\d{6}$", text)) {
            text = text.replace("+", "");
            sb.append(printFundCodeProcess.processFundCode(text, fromUserName));
        }

        return sb.toString();
    }
}
