package com.huiaong.bulbasau.rofficial.impl;

import cn.hutool.json.JSONUtil;
import com.huiaong.bulbasau.contains.MessageContains;
import com.huiaong.bulbasau.entity.Fund;
import com.huiaong.bulbasau.service.IRestService;
import com.huiaong.bulbasau.service.ITextProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(MessageContains.PROCESS_TEXT_FUND_CODE)
public class FundCodeProcessImpl implements ITextProcess {

    @Autowired
    private IRestService restService;

    @Override
    public String processText(String text) {
        String request = restService.getRequest(text);

        int i = request.indexOf('{');
        int j = request.lastIndexOf('}');

        String json = request.substring(i, ++j);
        Fund fund = JSONUtil.toBean(json, Fund.class);

        StringBuilder sb = new StringBuilder();
        sb.append("基金名 基金代码 今日估值\n");
        sb.append(fund.getName()).append(" ");
        sb.append(fund.getFundcode()).append(" ");
        sb.append(fund.getGszzl()).append("\n");
        return sb.toString();
    }
}
