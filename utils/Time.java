package utils;

/**
 * Created by Andrej on 12.12.2017.
 */

public class Time {
    public static final long SECOND = 1000000000;

    public static long get(){
        return System.nanoTime();
    }
}
