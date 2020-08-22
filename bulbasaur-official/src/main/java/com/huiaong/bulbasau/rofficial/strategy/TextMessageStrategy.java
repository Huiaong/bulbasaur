package com.huiaong.bulbasau.rofficial.strategy;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.contains.ResponseContains;
import com.huiaong.bulbasau.entity.TextMessage;
import com.huiaong.bulbasau.rofficial.handler.TextProcessHandle;
import com.huiaong.bulbasau.rofficial.utils.MessageUtil;
import com.huiaong.bulbasau.strategy.IMessageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Component(MessageContains.REQ_MESSAGE_TYPE_TEXT)
public class TextMessageStrategy implements IMessageStrategy {

    @Autowired
    private TextProcessHandle textProcessHandle;

    @Override
    public String processingMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");

        TextMessage txtmsg = new TextMessage();
        txtmsg.setToUserName(fromUserName);
        txtmsg.setFromUserName(toUserName);
        txtmsg.setCreateTime(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        txtmsg.setMsgType(ResponseContains.RESP_MESSAGE_TYPE_TEXT);

        String content = map.get("Content");
        String text = textProcessHandle.handleText(content, fromUserName);
        txtmsg.setContent(text);

        return MessageUtil.textMessageToXml(txtmsg);
    }
}
