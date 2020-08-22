package com.huiaong.bulbasau.strategy;

import java.util.Map;

public interface IMessageEventStrategy {


    String process(Map<String, String> map);
}
