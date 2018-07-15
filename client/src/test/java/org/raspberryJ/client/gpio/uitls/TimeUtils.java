package org.raspberryJ.client.gpio.uitls;

import org.junit.Assert;
import org.junit.Test;

public class TimeUtils {


    @Test
    public void scheduling() {
        long turnOnTimeDelay = org.cuixe.raspberryJ.gpio.utils.TimeUtils.getRemainingTime(18, 0, 0);
        if(turnOnTimeDelay < 0) {
            turnOnTimeDelay = 0;
        }
        long turnOffTimeDelay = org.cuixe.raspberryJ.gpio.utils.TimeUtils.getRemainingTime(1,01, 00, 0);
        if(turnOffTimeDelay < 0) {
            turnOffTimeDelay = 1;
        }
        System.out.println(turnOnTimeDelay);
        System.out.println(turnOffTimeDelay);


        String[] tokens = "24:00:00".split(":");
        int hours = Integer.valueOf(tokens[0]);
        int days = hours/24;
        hours = hours % 24;

        Assert.assertEquals(1, days);
        Assert.assertEquals(0, hours);
    }
}
