
public class Test {

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

        int b = 2;
        System.out.println(b <<= 1);
        int c = 2;
        System.out.println(c >>= 1);


        // System.out.println(Math.floor(12.92F));
        // System.out.println(Math.ceil(12.92F));
    }
}




