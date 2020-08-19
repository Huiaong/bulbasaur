package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.service.IEventStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(MessageContains.EVENT_TYPE_UNSUBSCRIBE)
public class UnsubscribeEventStrategy implements IEventStrategy {
    @Override
    public String processingEvent(Map<String, String> map) {
        return null;
    }
}