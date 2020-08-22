package com.huiaong.bulbasau.rofficial.strategy;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.strategy.IEventStrategy;
import com.huiaong.bulbasau.strategy.IMessageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component(MessageContains.REQ_MESSAGE_TYPE_EVENT)
public class EventMessageStrategy implements IMessageStrategy {

    @Autowired
    private final Map<String, IEventStrategy> strategyMap = new ConcurrentHashMap<>();

    @Override
    public String processingMessage(Map<String, String> map) {
        return this.strategyMap.get(map.get("Event")).processingEvent(map);
    }

}
