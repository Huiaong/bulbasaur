package com.huiaong.bulbasau.rofficial.strategy;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.service.ITextProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TextProcessHandle {

    @Autowired
    private final Map<String, ITextProcess> strategyMap = new ConcurrentHashMap<>();

    public String handleText(String text) {

        return this.strategyMap.get(MessageContains.process(text)).processText(text);
    }
}
