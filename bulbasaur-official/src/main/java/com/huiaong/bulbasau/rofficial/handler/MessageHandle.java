package com.huiaong.bulbasau.rofficial.handler;

import com.huiaong.bulbasau.strategy.IMessageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessageHandle {

    @Autowired
    private final Map<String, IMessageStrategy> strategyMap = new ConcurrentHashMap<>();

    public String process(Map<String, String> map) {
        return this.strategyMap.get(map.get("MsgType")).processingMessage(map);
    }

}
