package org.luncert;

public class ExceptionUtil {

    public static void handle(Exception e) {
        throw new RuntimeException(e);
    }
}