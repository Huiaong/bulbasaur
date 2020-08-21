package com.huiaong.bulbasau.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessToken implements Serializable {

    private String access_token;

    private Integer expires_in;

    private Integer errcode;

    private String errmsg;

}
