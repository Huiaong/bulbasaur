package com.huiaong.bulbasau.rofficial.dispatcher;

import com.huiaong.bulbasau.rofficial.strategy.EventHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class EventDispatcher {

    @Autowired
    private EventHandle eventHandle;

    public String processEvent(Map<String, String> map) {
        return eventHandle.handleEvent(map.get("Event"), map);
    }
}