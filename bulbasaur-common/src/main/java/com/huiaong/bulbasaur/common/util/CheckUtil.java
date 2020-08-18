package com.huiaong.bulbasaur.common.util;

import java.util.Arrays;

/**
 * @author :  Hujc QQ: 2679001462
 * @version : 1.0.1
 * @date : 2020/5/6/0006 12:23
 * @description :
 */
public class CheckUtil {
    private static final String token = "huiaong";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] str = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuilder buffer = new StringBuilder();
        Arrays.stream(str).forEach(buffer::append);
        //进行sha1加密
        String temp = SHA1.encode(buffer.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }
}
