package com.huiaong.bulbasau.rofficial.strategy;

import com.huiaong.bulbasau.service.IEventStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventHandle {

    @Autowired
    private final Map<String, IEventStrategy> strategyMap = new ConcurrentHashMap<>();

    public String handleEvent(String mode, Map<String, String> map) {
        return this.strategyMap.get(mode).processingEvent(map);
    }

}
