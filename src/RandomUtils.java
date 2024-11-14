import java.util.Random;

public class RandomUtils {
    public static int getRandomNumberWithNDigits(int difficulty) {
        Random random = new Random();
        int min = (int) Math.pow(10, difficulty - 1);
        int max = (int) Math.pow(10, difficulty) - 1;

        return random.nextInt(max - min + 1) + min;
    }
}
