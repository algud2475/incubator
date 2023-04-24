package utils;

import java.util.Random;

public class RandomUtil {
    public static int getRandomLimitedNumber(int max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }
}
