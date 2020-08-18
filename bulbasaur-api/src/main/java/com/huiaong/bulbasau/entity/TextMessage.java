package com.huiaong.bulbasau.entity;

import com.huiaong.bulbasaur.common.base.BaseMessage;

public class TextMessage extends BaseMessage {
    // 消息内容   
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}