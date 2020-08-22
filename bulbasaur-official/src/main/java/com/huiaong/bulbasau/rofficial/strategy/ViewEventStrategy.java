package com.huiaong.bulbasau.rofficial.strategy;

import com.huiaong.bulbasau.contains.EventContains;
import com.huiaong.bulbasau.strategy.IEventStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(EventContains.EVENT_TYPE_VIEW)
public class ViewEventStrategy implements IEventStrategy {
    @Override
    public String processingEvent(Map<String, String> map) {
        return null;
    }
}
