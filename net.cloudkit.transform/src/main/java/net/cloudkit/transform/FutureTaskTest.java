package net.cloudkit.transform;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * http://www.jianshu.com/p/1dd951412ff5
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public class FutureTaskTest {

    public static void main(String[] args) throws InterruptedException{
        FutureTask<Integer> ft = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int num = new Random().nextInt(10);
                TimeUnit.SECONDS.sleep(num);
                return num;
            }
        });
        Thread t = new Thread(ft);
        t.start();

        // 这里可以做一些其它的事情，跟futureTask任务并行，等需要futureTask的运行结果时，可以调用get方法获取。
        try {
            // 等待任务执行完成，获取返回值
            Integer num = ft.get();
            System.out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
