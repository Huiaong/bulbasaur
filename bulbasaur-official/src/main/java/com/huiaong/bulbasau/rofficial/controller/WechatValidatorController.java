package com.huiaong.bulbasau.rofficial.controller;

import com.google.common.base.Throwables;
import com.huiaong.bulbasaur.common.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public void validate(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.info("throws {}", Throwables.getStackTraceAsString(e));
        }
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        try (PrintWriter out = response.getWriter()) {
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                out.write(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
