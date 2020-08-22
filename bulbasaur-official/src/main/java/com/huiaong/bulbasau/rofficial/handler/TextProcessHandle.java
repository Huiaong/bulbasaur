package com.huiaong.bulbasau.rofficial.handler;

import cn.hutool.core.util.StrUtil;
import com.huiaong.bulbasau.contains.TextProcessContains;
import com.huiaong.bulbasau.process.ITextProcess;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Component
public class TextProcessHandle {
    public static final String[] QUESTION_MARK = {"?", "？"};
    public static final String[] FUND_CODE_RE = {"^\\d{6}$", "^\\+$", "^\\+\\d{6}$", "^-$", "^-\\d{6}$"};
    public static final String[] FUND_CODE_LIST = {"list", "l", "我的"};


    @Autowired
    private final Map<String, ITextProcess> strategyMap = new ConcurrentHashMap<>();

    public static String process(String text) {
        if (Stream.of(QUESTION_MARK).anyMatch(e -> StrUtil.equals(e, text))) {
            return TextProcessContains.PROCESS_QUESTION_MARK;
        } else if (Stream.of(FUND_CODE_RE).anyMatch(e -> REUtil.matches(e, text))) {
            return TextProcessContains.PROCESS_TEXT_FUND_CODE;
        } else if (Stream.of(FUND_CODE_LIST).anyMatch(e -> StrUtil.equals(e, text))) {
            return TextProcessContains.PROCESS_LIST;
        }
        return TextProcessContains.PROCESS_TEXT_SHIT;
    }

    public String handleText(String text, String fromUserName) {

        return this.strategyMap.get(process(text)).processText(text, fromUserName);
    }
}
