package com.huiaong.bulbasau.rofficial.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.huiaong.bulbasau.rofficial.properties.URLProperties;
import com.huiaong.bulbasau.service.IRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImpl implements IRestService {

    @Autowired
    private URLProperties urlProperties;

    @Override
    public String getRequest(String fundCode) {
        HttpResponse execute = HttpUtil.createGet(urlProperties.getFundIncrease(fundCode))
                .execute();
        return execute.body();
    }
}
