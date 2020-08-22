package com.huiaong.bulbasau.strategy;

import java.util.Map;

public interface IMessageStrategy {

    String processingMessage(Map<String, String> map);
}
