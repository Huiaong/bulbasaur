package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.service.IBulbasaurUserService;
import com.huiaong.bulbasau.service.IEventStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(MessageContains.EVENT_TYPE_UNSUBSCRIBE)
public class UnsubscribeEventStrategy implements IEventStrategy {

    @Autowired
    private IBulbasaurUserService userService;

    @Override
    public String processingEvent(Map<String, String> map) {
        String userOpenId = map.get("FromUserName");

        Boolean userExist = userService.userExistByOpenId(userOpenId);
        if (userExist) {
            userService.deleteUserByOpenId(userOpenId);
        }
        return null;
    }
}
