package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.entity.TextMessage;
import com.huiaong.bulbasau.rofficial.utils.MessageUtil;
import com.huiaong.bulbasau.service.IMessageStrategy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component(MessageContains.REQ_MESSAGE_TYPE_TEXT)
public class TextMessageStrategy implements IMessageStrategy {
    @Override
    public String processingMessage(Map<String, String> map) {
        TextMessage txtmsg = new TextMessage();
        txtmsg.setToUserName(map.get("FromUserName"));
        txtmsg.setFromUserName(map.get("ToUserName"));
        txtmsg.setCreateTime(new Date().getTime());
        txtmsg.setMsgType(MessageContains.RESP_MESSAGE_TYPE_TEXT);

        txtmsg.setContent("hello world");
        return MessageUtil.textMessageToXml(txtmsg);
    }
}
