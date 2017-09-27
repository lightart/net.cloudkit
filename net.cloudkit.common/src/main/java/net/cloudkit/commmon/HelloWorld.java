package net.cloudkit.commmon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HelloWorld
 */
public class HelloWorld {

    private static final Logger LOGGER = Logger.getLogger("HelloWorld");

    public static void main(String[] args) {
        LOGGER.log(Level.ALL, "Hello World!");
        System.out.println("Hello World!");
    }
}
