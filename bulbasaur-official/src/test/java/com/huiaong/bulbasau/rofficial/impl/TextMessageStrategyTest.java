package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.rofficial.BulbasaurOfficialApplicationTests;
import com.huiaong.bulbasau.strategy.IMessageStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TextMessageStrategyTest extends BulbasaurOfficialApplicationTests {

    @Autowired
    @Qualifier(MessageContains.REQ_MESSAGE_TYPE_TEXT)
    private IMessageStrategy messageStrategy;

    private Map<String, String> map = new HashMap<>();


    @Before
    public void before() {
        map.put("FromUserName", "123");
        map.put("ToUserName", "123");
        map.put("Content", "aaa");
    }

    @Test
    public void testProcessingMessage() {
        String request = messageStrategy.processingMessage(map);
        log.info(request);
    }
}