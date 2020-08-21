package com.huiaong.bulbasau.rofficial.dispatcher;

import com.huiaong.bulbasau.rofficial.strategy.TextProcessHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TextProcessDispatcher {

    @Autowired
    private TextProcessHandle textProcessHandle;

    public String dispatch(String content, String fromUserName) {
        return textProcessHandle.handleText(content, fromUserName);
    }
}
