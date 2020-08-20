package com.huiaong.bulbasau.rofficial;

import cn.hutool.core.util.StrUtil;
import com.huiaong.bulbasau.rofficial.properties.RedissonProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.huiaong.bulbasau.rofficial.dao")
public class BulbasaurOfficialConfiguration {

    @Autowired
    private RedissonProperties redissonProperties;

    @Bean
    RedissonClient redissonSingle() {
        Config config = new Config();

        String node = "redis://".concat(redissonProperties.getHost()).concat(":").concat(redissonProperties.getPort());
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node);
        if (StrUtil.isNotEmpty(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }
}
