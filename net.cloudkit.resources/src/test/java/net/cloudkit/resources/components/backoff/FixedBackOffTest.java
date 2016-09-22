package net.cloudkit.resources.components.backoff;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FixedBackOffTest {

    @Test
    public void testFixedBackOff() {
        long interval = 100;
        long maxAttempts = 10;
        BackOff backOff = new FixedBackOff(interval, maxAttempts);
        BackOffExecution execution = backOff.start();

        for(int i = 1; i <= 10; i++) {
            //每次重试时间是100毫秒
            System.out.println(execution.nextBackOff());
        }
        Assert.assertEquals(BackOffExecution.STOP, execution.nextBackOff());
    }
}
