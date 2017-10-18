package net.cloudkit.commmon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HelloWorld
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public class HelloWorld {

    private static final Logger LOGGER = Logger.getLogger("HelloWorld");

    public static void main(String[] args) {
        LOGGER.log(Level.ALL, "Hello World!");
        System.out.println("Hello World!");
    }
}
