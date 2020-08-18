package com.huiaong.bulbasau.rofficial.strategy;

import com.huiaong.bulbasau.service.IMessageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessageHandle {

    @Autowired
    private final Map<String, IMessageStrategy> strategyMap = new ConcurrentHashMap<>();

    public String handleMessage(String mode, Map<String, String> map) {
        return this.strategyMap.get(mode).processingMessage(map);
    }

}
