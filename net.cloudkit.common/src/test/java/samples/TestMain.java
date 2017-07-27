package samples;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        // System.out.println(new samples.TransClass().getNumber());

        System.out.println(new TransClass().getNumber());
        int count = 0;
        while (true) {
            Thread.sleep(500);
            count++;
            int number = new TransClass().getNumber();
            System.out.println(number);
            if (3 == number || count >= 10) {
                break;
            }
        }
    }
}
