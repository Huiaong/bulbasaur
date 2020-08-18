package com.huiaong.bulbasau.service;

import java.util.Map;

public interface IEventStrategy {

    String processingEvent(Map<String, String> map);
}
