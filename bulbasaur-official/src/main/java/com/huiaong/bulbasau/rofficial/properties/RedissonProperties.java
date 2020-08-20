package com.huiaong.bulbasau.rofficial.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonProperties {

    private String host;

    private String port;

    private String password;

}
