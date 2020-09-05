package com.huiaong.bulbasau.rofficial.controller;

import cn.hutool.json.JSONUtil;
import com.google.common.base.Throwables;
import com.huiaong.bulbasau.rofficial.handler.MessageHandle;
import com.huiaong.bulbasau.rofficial.utils.MessageUtil;
import com.huiaong.bulbasaur.common.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author :  Hujc QQ: 2679001462
 * @version : 1.0.1
 * @date : 2020/5/6/0006 8:50
 * @description :
 */

@Slf4j
@RestController
@RequestMapping("/api/validator/wechat")
public class WechatValidatorController {

    @Autowired
    private MessageHandle messageHandle;


    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    public void validate(HttpServletRequest request, HttpServletResponse response) {
        log.info("start validate !!!");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        try (PrintWriter out = response.getWriter()) {
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                log.info("success check signature !!!");
                out.write(echostr);
            } else {
                log.error("failed check signature !!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/keyword", method = RequestMethod.POST)
    public String keyword(HttpServletRequest request) {
        try {
            Map<String, String> map = MessageUtil.parseXml(request);
            log.info("map:{}", JSONUtil.toJsonStr(map));

            return messageHandle.process(map); //进入消息处理
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
        }
        return null;
    }


}
