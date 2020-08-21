package com.huiaong.bulbasau.rofficial.properties;

import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "urls")
public class URLProperties {

    private String fundIncrease;

    private String menuGenerate;

    private String accessToken;

    private String appid;

    private String appSecret;

    public String getAccessToken() {
        return accessToken.replace("APPID", appid).replace("APPSECRET", appSecret);
    }

    public String getMenuGenerate(String replacement) {
        return menuGenerate.replace("ACCESS_TOKEN", replacement);
    }

    public String getFundIncrease(String replacement) {
        return fundIncrease.replace("FUND_CODE", replacement);
    }
}
