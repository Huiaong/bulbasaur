package com.huiaong.bulbasau.rofficial.strategy;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.strategy.IMessageStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(MessageContains.REQ_MESSAGE_TYPE_LOCATION)
public class LocationMessageStrategy implements IMessageStrategy {
    @Override
    public String processingMessage(Map<String, String> map) {
        return null;
    }
}
