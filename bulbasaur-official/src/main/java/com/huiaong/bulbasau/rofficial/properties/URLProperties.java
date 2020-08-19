package com.huiaong.bulbasau.rofficial.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "urls")
public class URLProperties {

    private String fundIncrease;

    public String getFundIncrease(String replacement) {
        String fund_code = fundIncrease.replace("FUND_CODE", replacement);
        log.info(fund_code);
        return fund_code;
    }
}