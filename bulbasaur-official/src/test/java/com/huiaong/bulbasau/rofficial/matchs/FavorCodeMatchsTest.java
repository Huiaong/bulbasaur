package com.huiaong.bulbasau.rofficial.matchs;

import cn.hutool.core.lang.Assert;
import com.huiaong.bulbasau.rofficial.BulbasaurOfficialApplicationTests;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import org.junit.Test;

public class FavorCodeMatchsTest extends BulbasaurOfficialApplicationTests {

    @Test
    public void match() {
        String text = "+008888";
        boolean matches = REUtil.matches("^\\+$|^\\+\\d{6}$", text);
        Assert.isTrue(matches);
    }

}
