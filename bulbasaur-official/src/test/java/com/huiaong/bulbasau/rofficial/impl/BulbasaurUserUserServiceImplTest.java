package com.huiaong.bulbasau.rofficial.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.huiaong.bulbasau.entity.User;
import com.huiaong.bulbasau.rofficial.BulbasaurOfficialApplicationTests;
import com.huiaong.bulbasau.service.IBulbasaurUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j
public class BulbasaurUserUserServiceImplTest extends BulbasaurOfficialApplicationTests {

    @Autowired
    private IBulbasaurUserService userService;

    @Test
    public void findById() {
        User user = userService.findById(1L);
        log.info(JSONUtil.toJsonStr(user));
    }

    @Test
    public void create() {
        User user = new User();
        user.setOpenId("123");
        user.setStatus(1);
        user.setCreateAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
        user.setDelFlag(1);

        Boolean createSuccess = userService.create(user);
        Assert.isTrue(createSuccess);
    }

    @Test
    public void testUserExistByOpenId() {
        Boolean userExist = userService.userExistByOpenId("oe_m4wkS9ZM6YUXjbHtC2gROy5_s");
        Assert.isTrue(userExist);
    }

    public void testDeleteUserByOpenId() {
    }
}