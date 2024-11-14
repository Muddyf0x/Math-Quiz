import java.util.Random;

/**
 * Utility class for generating random numbers.
 */
public class RandomUtils {

    /**
     * Generates a random integer with a specified number of digits.
     *
     * @param difficulty The number of digits for the random number. Must be greater than 0.
     *                   For example, if difficulty is 3, the result will be a 3-digit number.
     * @return A random integer with the specified number of digits.
     *         If difficulty is 1, returns a number between 1 and 9.
     *         If difficulty is 2, returns a number between 10 and 99, and so on.
     * @throws IllegalArgumentException if the difficulty is less than 1.
     */
    public static int getRandomNumberWithNDigits(int difficulty) {
        if (difficulty < 1) {
            throw new IllegalArgumentException("Difficulty must be at least 1.");
        }

        Random random = new Random();
        int min = (int) Math.pow(10, difficulty - 1); // Minimum value with the specified digits
        int max = (int) Math.pow(10, difficulty) - 1; // Maximum value with the specified digits

        return random.nextInt(max - min + 1) + min; // Random number in range [min, max]
    }
}
