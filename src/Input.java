import java.util.Scanner;
/**
 *This class gives a variety of input methods for the primitives used by the main game.
 * Note: The input source is defined in Constants and is the same for all functions!
 */
public class Input {
    private final static Scanner scan = new Scanner(Constants.INPUT_SOURCE);

    /**
     * Reads an integer from the input.
     * Retries until a valid integer is entered.
     * @return The validated integer input from the user.
     */
    public static int getIntegerIn() {
        int input;
        do {
            try {
                input = scan.nextInt();
                return input;
            } catch (Exception e) {
                System.out.print("Invalid integer. Please enter a valid number: ");
                scan.nextLine();
            }
        } while (true);
    }

    /**
     * Reads a long from the input.
     * Retries until a valid long is entered.
     * @return The validated long input from the user.
     */
    public static long getLongIn() {
        long input;
        do {
            try {
                input = scan.nextLong();
                return input;
            } catch (Exception e) {
                System.out.print("Invalid long. Please enter a valid number: ");
                scan.nextLine();
            }
        } while (true);
    }

    /**
     * Reads a String from the input.
     * @return The striped and lowercased input from the user.
     */
    public static String getLowerCaseString() {
        String str;
        str = scan.next();
        return str.toLowerCase().strip();
    }

    /**
     * Close the scanner at the end of the programm
     */
    public static void closeScanner() {
        scan.close();
    }
}
