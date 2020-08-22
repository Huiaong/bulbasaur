package com.huiaong.bulbasau.rofficial.process;

import cn.hutool.core.collection.CollectionUtil;
import com.huiaong.bulbasau.contains.TextProcessContains;
import com.huiaong.bulbasau.entity.FavorFund;
import com.huiaong.bulbasau.process.ITextProcess;
import com.huiaong.bulbasau.service.IBulbasaurFavorFundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component(TextProcessContains.PROCESS_LIST)
public class ListProcessImpl implements ITextProcess {

    @Autowired
    private PrintFavorFundCodeProcessImpl printFavorCodeProcess;

    @Autowired
    private IBulbasaurFavorFundService favorFundService;

    @Override
    public String processText(String text, String fromUserName) {
        log.info("Handling list fund code process, {} by {}", text, fromUserName);

        StringBuffer sb = new StringBuffer();

        List<FavorFund> favorFunds = favorFundService.findByOpenId(fromUserName);
        if (CollectionUtil.isEmpty(favorFunds)) {
            log.error("favor fund code is null, {} by {}", text, favorFunds);
            return sb.append("暂无关注的基金").toString();
        }

        favorFunds.stream().map(FavorFund::getFundCode).forEach(e -> sb.append(printFavorCodeProcess.justPrint(e)).append("\n"));

        return sb.toString();
    }
}
