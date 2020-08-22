package com.huiaong.bulbasau.rofficial.process;

import com.huiaong.bulbasau.contains.FavorFundCodeContains;
import com.huiaong.bulbasau.contains.TextProcessContains;
import com.huiaong.bulbasau.process.IFavorFundCodeProcess;
import com.huiaong.bulbasau.process.ITextProcess;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Slf4j
@Component(TextProcessContains.PROCESS_TEXT_FUND_CODE)
public class FundCodeProcessImpl implements ITextProcess {
    public static final String[] FUND_CODE_ADD_RE = {"^\\+$", "^\\+\\d{6}$"};
    public static final String[] FUND_CODE_SUB_RE = {"^-$", "^-\\d{6}$"};

    @Autowired
    private final Map<String, IFavorFundCodeProcess> strategyMap = new ConcurrentHashMap<>();

    public static String process(String text) {
        if (Stream.of(FUND_CODE_ADD_RE).anyMatch(e -> REUtil.matches(e, text))) {
            return FavorFundCodeContains.FAVOR_FUND_CODE_ADD;
        } else if (Stream.of(FUND_CODE_SUB_RE).anyMatch(e -> REUtil.matches(e, text))) {
            return FavorFundCodeContains.FAVOR_FUND_CODE_SUB;
        }
        return FavorFundCodeContains.FAVOR_FUND_CODE_PRINT;
    }

    @Override
    public String processText(String text, String fromUserName) {
        log.info("Handling fund code process, {} by {}", text, fromUserName);
        return this.strategyMap.get(process(text)).processFundCode(text, fromUserName);
    }

}
