package com.huiaong.bulbasau.rofficial.strategy;

import com.huiaong.bulbasau.contains.EventContains;
import com.huiaong.bulbasau.contains.ResponseContains;
import com.huiaong.bulbasau.entity.TextMessage;
import com.huiaong.bulbasau.entity.User;
import com.huiaong.bulbasau.rofficial.utils.MessageUtil;
import com.huiaong.bulbasau.service.IBulbasaurUserService;
import com.huiaong.bulbasau.strategy.IEventStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Component(EventContains.EVENT_TYPE_SUBSCRIBE)
public class SubscribeEventStrategy implements IEventStrategy {

    @Autowired
    private IBulbasaurUserService userService;

    @Override
    public String processingEvent(Map<String, String> map) {
        String userOpenId = map.get("FromUserName");
        String createAt = map.get("CreateTime");
        String toUserName = map.get("ToUserName");
        String content = "欢迎关注狐妖昂昂[Respect]\n快回复'?'查看公众号玩法吧";

        TextMessage txtmsg = new TextMessage();
        txtmsg.setFromUserName(toUserName);
        txtmsg.setToUserName(userOpenId);
        txtmsg.setCreateTime(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        txtmsg.setMsgType(ResponseContains.RESP_MESSAGE_TYPE_TEXT);

        Boolean userExist = userService.userExistByOpenId(userOpenId);
        if (!userExist) {
            User user = new User();
            user.setOpenId(userOpenId);
            user.setDelFlag(1);
            user.setStatus(1);
            LocalDateTime now = Instant.ofEpochMilli(Long.parseLong(createAt)).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
            user.setCreateAt(now);
            user.setUpdateAt(now);
            Boolean createSuccess = userService.create(user);
            if (!createSuccess) {
                content = "用户信息记录失败，重新关注公众号试试\uD83E\uDD14";
            }
        }

        txtmsg.setContent(content);

        return MessageUtil.textMessageToXml(txtmsg);
    }
}
