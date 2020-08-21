package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.service.ITextProcess;
import org.springframework.stereotype.Component;

@Component(MessageContains.PROCESS_TEXT_SHIT)
public class ShitTextProcessImpl implements ITextProcess {

    @Override
    public String processText(String text, String fromUserName) {
        return "\" " + text + " \"是个\uD83D\uDCA9\n还不赶紧发\"?\"?";
    }
}
