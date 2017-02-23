import com.sun.xml.internal.fastinfoset.algorithm.UUIDEncodingAlgorithm;

import java.math.BigInteger;
import java.util.UUID;

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

        // UUIDEncodingAlgorithm
        UUID initUUID = UUID.randomUUID();
        long uniqueIdentifier = initUUID.getLeastSignificantBits() + initUUID.getMostSignificantBits();
        System.out.println(uniqueIdentifier);

        /*
         两个素数：p = 47, q = 59
         n = p * q = 2773
         t = (p - 1) * (q - 1) = 2668
         取e = 63，满足e < t并且e和t互素
         用perl简单穷举可以获得满足 e * d % t == 1的数d：
         C:\Temp>perl -e "foreach $i (1..9999){ print($i),last if $i*63%2668==1 }"
         847
         即d＝847
         最终获得：n = 2773, d(公钥) = 847, e(私钥) = 63
         取消息m = 244
         加密c = m ** d % n = 244 ** 847 % 2773 即 c = d ^ m % n = 244 ** 847 % 2773
         解密m = c ** e % n = 465 ** 63 % 2773 即 m = e ^ c % n = 465 ** 63 % 2773
         */
        System.out.println(
            new BigInteger("244")
            .pow(847)
            .remainder(new BigInteger("2773")));
        System.out.println(
            new BigInteger("465")
            .pow(63)
            .remainder(new BigInteger("2773")));

        System.out.println(
            new BigInteger("244")
                .pow(63)
                .remainder(new BigInteger("2773")));
        System.out.println(
            new BigInteger("465")
            .pow(63)
            .remainder(new BigInteger("2773")));

        System.out.println(
            new BigInteger("244")
                .pow(63)
                .remainder(new BigInteger("2773")));
        System.out.println(new BigInteger("465").pow(847).remainder(new BigInteger("2773")));

        System.out.println(new BigInteger("244").pow(847).remainder(new BigInteger("2773")));
        System.out.println(new BigInteger("465").pow(847).remainder(new BigInteger("2773")));
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



