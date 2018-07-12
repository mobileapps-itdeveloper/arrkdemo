package com.arrktest.arrkdemo;

import com.arrktest.arrkdemo.classes.Utility;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CommonUnitTest {
    @Test
    public void verify_getMetersFromCM() {
        assertThat(Utility.getMetersFromCM(1), Is.is(IsEqual.equalTo(0.01d)));
        assertThat(Utility.getMetersFromCM(10000), Is.is(IsEqual.equalTo(100d)));
    }


}