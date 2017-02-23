
public class Test {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static void main(String[] args) {
        // Package pkg = Test.class.getPackage();
        // System.out.println((pkg != null ? pkg.getImplementationVersion() : null));


        // 位运算
        /*
        int a = 100;
        for (int i = 0; i < 100000000; i++) {
            a <<= 1;// 向高位移动一位，相当于乘以 2 的1次方
            a >>= 1; // 向低位移动一位，相当于 除以 2的 1次方
        }
        */

        /*
        int b = 2;
        System.out.println(b <<= 1);
        int c = 2;
        System.out.println(c >>= 1);
        */

        // System.out.println(Math.floor(12.92F));
        // System.out.println(Math.ceil(12.92F));

        int x = 1;    // x指向内存地址A，内容是整数1
        int y = x;    // y指向同样的内存地址A，内容是整数1
        x = 2;        // x指向另一个内存地址B，内容是整数2。y仍然指向地址A，内容是1。
        System.out.println(y);
        System.out.println(x);

        Integer x_1 = 1;
        Integer y_1 = x_1;
        x_1 = 2;
        System.out.println(y_1);
        System.out.println(x_1);
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}



