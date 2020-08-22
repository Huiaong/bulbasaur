package com.huiaong.bulbasau.rofficial.matchs;

import cn.hutool.core.lang.Assert;
import com.huiaong.bulbasau.rofficial.BulbasaurOfficialApplicationTests;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import org.junit.Test;

import java.util.stream.Stream;

public class FavorCodeMatchsTest extends BulbasaurOfficialApplicationTests {

    public static final String[] FUND_CODE_ADD_RE = {"^\\+$", "^\\+\\d{6}$"};

    @Test
    public void match() {
        String text = "+008888";
        boolean matches = Stream.of(FUND_CODE_ADD_RE).anyMatch(e -> REUtil.matches(e, text));
        Assert.isTrue(matches);
    }

}
