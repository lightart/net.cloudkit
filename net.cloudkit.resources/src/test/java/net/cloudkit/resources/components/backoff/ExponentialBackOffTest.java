package net.cloudkit.resources.components.backoff;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExponentialBackOffTest {

    @Test
    public void testExponentialBackOff() {
        // 初始间隔
        long initialInterval = 100;
        // 最大间隔
        long maxInterval = 5 * 1000L;
        // 最大时间间隔
        long maxElapsedTime = 50 * 1000L;
        // 递增倍数（即下次间隔是上次的多少倍）
        double multiplier = 1.5;
        ExponentialBackOff backOff = new ExponentialBackOff(initialInterval, multiplier);
        backOff.setMaxInterval(maxInterval);
        // currentElapsedTime = interval1 + interval2 + ... + intervalN;
        backOff.setMaxElapsedTime(maxElapsedTime);

        BackOffExecution execution = backOff.start();

        for(int i = 1; i <= 18; i++) {
            System.out.println(execution.nextBackOff());
        }
        Assert.assertEquals(BackOffExecution.STOP, execution.nextBackOff());
    }
}
