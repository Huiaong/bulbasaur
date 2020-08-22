package com.huiaong.bulbasau.rofficial.process;

import com.huiaong.bulbasau.contains.TextProcessContains;
import com.huiaong.bulbasau.process.ITextProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(TextProcessContains.PROCESS_QUESTION_MARK)
public class QuestionMarkTextProcessImpl implements ITextProcess {

    @Override
    public String processText(String text, String fromUserName) {
        log.info("Handling tips, {} by {}", text, fromUserName);
        return "回复基金代码获取今日基金估值" +
                "\n\n" +
                "回复\"+\"或\"+008888\"收藏基金" +
                "\n\n" +
                "回复\"-\"或\"-008888\"取消收藏基金" +
                "\n\n" +
                "回复\"list、l、我的\"查看已收藏基金";
    }
}
