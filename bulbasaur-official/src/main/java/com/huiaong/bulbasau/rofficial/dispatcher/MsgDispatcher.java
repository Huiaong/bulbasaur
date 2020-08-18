package com.huiaong.bulbasau.rofficial.dispatcher;

import com.huiaong.bulbasau.rofficial.strategy.MessageHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class MsgDispatcher {

    @Autowired
    private MessageHandle messageHandle;

    public String processMessage(Map<String, String> map) {
        return messageHandle.handleMessage(map.get("MsgType"), map);
    }
}