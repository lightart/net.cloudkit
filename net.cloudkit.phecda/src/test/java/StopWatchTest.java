import org.springframework.util.StopWatch;

public class StopWatchTest {

    public static void main(String[] args) throws InterruptedException{
        StopWatch first = new StopWatch("First");
        first.start("A");
        Thread.sleep(200);
        first.stop();
        first.start("B");
        Thread.sleep(200);
        first.stop();
        first.start("C");
        Thread.sleep(120);
        first.stop();
        System.out.println(first.prettyPrint());

    }
}
