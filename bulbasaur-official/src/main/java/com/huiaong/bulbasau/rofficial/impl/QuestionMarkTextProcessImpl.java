package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.service.ITextProcess;
import org.springframework.stereotype.Component;

@Component(MessageContains.PROCESS_QUESTION_MARK)
public class QuestionMarkTextProcessImpl implements ITextProcess {

    @Override
    public String processText(String text) {
        return "回复基金代码获取今日基金估值";
    }
}
