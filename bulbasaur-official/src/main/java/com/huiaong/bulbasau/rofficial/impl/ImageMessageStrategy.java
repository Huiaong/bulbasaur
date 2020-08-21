package com.huiaong.bulbasau.rofficial.impl;

import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.entity.Article;
import com.huiaong.bulbasau.entity.NewsMessage;
import com.huiaong.bulbasau.rofficial.utils.MessageUtil;
import com.huiaong.bulbasau.service.IMessageStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(MessageContains.RESP_MESSAGE_TYPE_IMAGE)
public class ImageMessageStrategy implements IMessageStrategy {
    @Override
    public String processingMessage(Map<String, String> map) {
        NewsMessage newmsg = new NewsMessage();
        newmsg.setToUserName(map.get("FromUserName"));
        newmsg.setFromUserName(map.get("ToUserName"));
        newmsg.setCreateTime(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        newmsg.setMsgType(MessageContains.RESP_MESSAGE_TYPE_NEWS);

        Article article = new Article();
        article.setDescription("这是图文消息 1"); //图文消息的描述
        article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); //图文消息图片地址
        article.setTitle("图文消息 1");  //图文消息标题
        article.setUrl("http://www.cuiyongzhi.com");  //图文 url 链接
        List<Article> list = new ArrayList<Article>();
        list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
        newmsg.setArticleCount(list.size());
        newmsg.setArticles(list);
        return MessageUtil.newsMessageToXml(newmsg);
    }
}
