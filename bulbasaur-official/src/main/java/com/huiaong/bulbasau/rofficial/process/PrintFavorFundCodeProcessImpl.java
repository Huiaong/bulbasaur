package com.huiaong.bulbasau.rofficial.process;

import cn.hutool.json.JSONUtil;
import com.huiaong.bulbasau.contains.FavorFundCodeContains;
import com.huiaong.bulbasau.contains.FundCodeContains;
import com.huiaong.bulbasau.entity.Fund;
import com.huiaong.bulbasau.process.IFavorFundCodeProcess;
import com.huiaong.bulbasau.service.IRestService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component(FavorFundCodeContains.FAVOR_FUND_CODE_PRINT)
public class PrintFavorFundCodeProcessImpl implements IFavorFundCodeProcess {


    @Autowired
    private IRestService restService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    @Qualifier(FavorFundCodeContains.FAVOR_FUND_CODE_DEL)
    private IFavorFundCodeProcess deleteFundCodeProcess;

    @Autowired
    @Qualifier(FavorFundCodeContains.FAVOR_FUND_CODE_SAVE)
    private IFavorFundCodeProcess saveFundCodeProcess;

    @Override
    public String processFundCode(String text, String fromUserName) {
        log.info("Handling print fund code process, {} by {}", text, fromUserName);

        StringBuilder sb = new StringBuilder();

        RBucket<String> favorFundBucket = redissonClient.getBucket(FundCodeContains.OPTION_FAVOR_FUND_REDIS_KEY + fromUserName);

        if (Objects.equals(favorFundBucket.get(), FavorFundCodeContains.FAVOR_FUND_CODE_SUB)) {
            return sb.append(deleteFundCodeProcess.processFundCode(text, fromUserName)).toString();
        } else if (Objects.equals(favorFundBucket.get(), FavorFundCodeContains.FAVOR_FUND_CODE_ADD)) {
            sb.append(saveFundCodeProcess.processFundCode(text, fromUserName));
            sb.append("\n");
        }


        Optional<Fund> fundOptional = this.getFund(text);

        // 基金信息 获取失败
        if (!fundOptional.isPresent()) {
            return sb.append("基金信息获取失败，系统异常").toString();
        }
        Fund fund = fundOptional.get();

        sb.append("基金名 基金代码 今日估值\n");
        sb.append(fund.getName()).append(" ");
        sb.append(fund.getFundcode()).append(" ");
        sb.append(fund.getGszzl()).append("\n");
        return sb.toString();
    }

    private Optional<Fund> getFund(String text) {

        String request = restService.getRequest(text);

        // 从接口返回信息中截取基金json
        // jsonpgz({"fundcode":"008888","name":"华夏国证半导体芯片ETF联接C","jzrq":"2020-08-20","dwjz":"1.1388","gsz":"1.1317","gszzl":"-0.62","gztime":"2020-08-21 15:00"});
        int i = request.indexOf('{');
        int j = request.lastIndexOf('}');

        // json 格式错误
        if (i == -1 || j == -1) {
            log.error("failed to request fund by fund code:{}", text);
            return Optional.empty();
        }

        String json = request.substring(i, ++j);
        Fund fund = JSONUtil.toBean(json, Fund.class);
        return Optional.ofNullable(fund);
    }
}
