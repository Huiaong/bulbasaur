package com.huiaong.bulbasau.rofficial.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.huiaong.bulbasau.contains.FundCodeContains;
import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.entity.FavorFund;
import com.huiaong.bulbasau.entity.Fund;
import com.huiaong.bulbasau.entity.User;
import com.huiaong.bulbasau.service.IBulbasaurFavorFundService;
import com.huiaong.bulbasau.service.IBulbasaurUserService;
import com.huiaong.bulbasau.service.IRestService;
import com.huiaong.bulbasau.service.ITextProcess;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component(MessageContains.PROCESS_TEXT_FUND_CODE)
public class FundCodeProcessImpl implements ITextProcess {

    @Autowired
    private IRestService restService;
    @Autowired
    private IBulbasaurFavorFundService favorFundService;
    @Autowired
    private IBulbasaurUserService userService;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public String processText(String text, String fromUserName) {
        boolean startCollect = false;

        RBucket<String> favorFundBucket = redissonClient.getBucket(FundCodeContains.ADD_FAVOR_FUND_REDIS_KEY + fromUserName);

        StringBuilder sb = new StringBuilder();

        // 用户输入 "+ 六位基金代码" 立即收藏 基金代码 并 开始监听 基金收藏
        if (REUtil.matches("^\\+$|^\\+\\d{6}$", text)) {
            favorFundBucket.set(fromUserName, 5, TimeUnit.MINUTES);

            // 已开始收藏
            startCollect = true;

            sb.append("开始收藏5分钟内输入的基金");

            // 用户输入单个 "+" 已开始监听基金收藏 返回
            if (REUtil.matches("^\\+$", text)) {
                return sb.toString();
            }
            sb.append("\n");
            // 去除 "+"
            text = text.substring(1, 7);

            // 不返回 继续输出基金信息
        }


        // 查询是否应该收藏基金
        boolean shouldCollect = StrUtil.isNotEmpty(favorFundBucket.get());

        // 收藏基金
        if (startCollect || shouldCollect) {
            sb.append(this.collectFund(fromUserName, text));
        }

        Optional<Fund> fundOptional = this.getFund(text);

        // 基金信息 获取失败
        if (!fundOptional.isPresent()) {
            return sb.append("\n基金信息获取失败，系统异常").toString();
        }
        Fund fund = fundOptional.get();

        sb.append("基金名 基金代码 今日估值\n");
        sb.append(fund.getName()).append(" ");
        sb.append(fund.getFundcode()).append(" ");
        sb.append(fund.getGszzl()).append("\n");
        return sb.toString();
    }

    private String collectFund(String openId, String text) {
        // 增加favor fund 并开始监听

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
                return "\n当前基金收藏失败\n";
            } else {
                return "\n当前基金收藏成功\n";
            }
        } else {
            return "\n当前基金已收藏\n";
        }
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
