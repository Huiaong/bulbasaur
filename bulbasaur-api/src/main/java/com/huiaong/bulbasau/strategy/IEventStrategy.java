package com.huiaong.bulbasau.strategy;

import java.util.Map;

public interface IEventStrategy {

    String processingEvent(Map<String, String> map);
}
