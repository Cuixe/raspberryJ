package org.cuixe.raspberry.utils;

import org.junit.Assert;
import org.junit.Test;

public class TimeUtilsTest {

    @Test
    public void getDayTime() {
        long today = TimeUtils.getToDayTime();
        long time = TimeUtils.getNowTime();
        long now = System.currentTimeMillis();
        Assert.assertTrue(((today + time) - now) < 2);
        Assert.assertTrue(((today + time) - now) < 2);
    }

    @Test
    public void castStrings() {
        String stringTime = "18:00:00";
        long time = TimeUtils.castStringTime(stringTime);
        long nextTime = TimeUtils.getToDayTime() + time;
        String newTime = TimeUtils.formatTimeToString(nextTime);
        String today = TimeUtils.formatTimeToString(TimeUtils.getToDayTime());

        Assert.assertTrue(newTime.substring(0, 10).equals(today.substring(0, 10)));
        Assert.assertTrue(newTime.substring(11).equals(stringTime));
    }

}