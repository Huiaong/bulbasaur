package com.huiaong.bulbasau.rofficial.process;

import com.huiaong.bulbasau.contains.TextProcessContains;
import com.huiaong.bulbasau.process.ITextProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(TextProcessContains.PROCESS_MANAGER)
public class ManagerProcessImpl implements ITextProcess {
    @Override
    public String processText(String text, String fromUserName) {
        return null;
    }
}
