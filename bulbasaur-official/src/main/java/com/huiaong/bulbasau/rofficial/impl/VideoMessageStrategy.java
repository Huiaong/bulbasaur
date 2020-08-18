package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.service.IMessageStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(MessageContains.REQ_MESSAGE_TYPE_VIDEO)
public class VideoMessageStrategy implements IMessageStrategy {
    @Override
    public String processingMessage(Map<String, String> map) {
        return null;
    }
}
