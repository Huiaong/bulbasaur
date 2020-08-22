package com.huiaong.bulbasau.rofficial.process;

import com.huiaong.bulbasau.contains.TextProcessContains;
import com.huiaong.bulbasau.process.ITextProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(TextProcessContains.PROCESS_TEXT_SHIT)
public class ShitTextProcessImpl implements ITextProcess {

    @Override
    public String processText(String text, String fromUserName) {
        log.info("Handling shit text, {} by {}", text, fromUserName);
        return "\" " + text + " \"是个\uD83D\uDCA9\n还不赶紧发\"?\"?";
    }
}
