package com.huiaong.bulbasau.entity;

import com.huiaong.bulbasaur.common.base.BaseMessage;

public class MusicMessage extends BaseMessage {
    // 音乐   
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}