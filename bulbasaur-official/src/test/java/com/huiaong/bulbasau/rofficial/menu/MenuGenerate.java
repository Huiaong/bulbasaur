package com.huiaong.bulbasau.rofficial.menu;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.huiaong.bulbasau.entity.AccessToken;
import com.huiaong.bulbasau.rofficial.BulbasaurOfficialApplicationTests;
import com.huiaong.bulbasau.rofficial.properties.URLProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MenuGenerate extends BulbasaurOfficialApplicationTests {

    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String MENU_JSON = "{\"button\":[{\"type\":\"click\",\"name\":\"基金列表\",\"key\":\"fund_list\"}]}";

    @Autowired
    private URLProperties urlProperties;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void generate() {
        RBucket<String> tokenBucket = redissonClient.getBucket(ACCESS_TOKEN);
        String token = tokenBucket.get();
        if (StrUtil.isEmpty(token)) {

            HttpResponse accessTokenResponse = HttpUtil.createGet(urlProperties.getAccessToken()).execute();
            String accessTokenJson = accessTokenResponse.body();
            AccessToken accessToken = JSONUtil.toBean(accessTokenJson, AccessToken.class);
            if (Objects.nonNull(accessToken.getErrcode()) && !Objects.equals(accessToken.getErrcode(), 0)) {
                log.error("an error happens [{}]", accessToken.getErrmsg());
                return;
            }
            tokenBucket.set(accessToken.getAccess_token(), 7190L, TimeUnit.SECONDS);
            token = accessToken.getAccess_token();
        }

        HttpResponse menuResponse = HttpUtil.createPost(urlProperties.getMenuGenerate(token)).body(MENU_JSON).execute();
        String menuJson = menuResponse.body();
        Map<String, String> map = (Map<String, String>) JSONUtil.toBean(menuJson, Map.class);
        if (!Objects.equals(map.get("errcode"), String.valueOf(0))) {
            log.error("failed to create menu");
            return;
        }
        log.info("success to create menu");

    }

}
