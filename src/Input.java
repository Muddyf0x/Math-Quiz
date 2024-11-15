import java.util.Scanner;
import java.util.function.Supplier;

/// The Input class provides a set of methods for reading user input with validation.
/// It includes methods for obtaining validated integers, longs, and lowercased strings.
/// Note: This class uses a single input source, defined in the Constants class, which
///       is shared across all methods. The input source is intended to be set to System.in
///       for standard usage but can be redirected to other sources (e.g., for testing).
public class Input {
    // Single scanner instance for reading input across the class
    private final static Scanner scan = new Scanner(Constants.INPUT_SOURCE);

    /**
     * Generalized method to read and validate input using a Supplier.
     * This method will repeatedly prompt the user for input until a valid value
     * is returned, according to the supplier provided.
     *
     * @param inputSupplier A Supplier that defines the input retrieval logic, such as scan::nextInt.
     * @param errorMessage  The message displayed if an invalid input is entered.
     * @param <T>           The type of input expected (e.g., Integer, Long).
     * @return A valid input value of type T, provided by inputSupplier.
     */
    public static <T> T getInput(Supplier<T> inputSupplier, String errorMessage) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (Exception e) {
                System.out.print(errorMessage);
                scan.nextLine();
            }
        }
    }
    /**
     * Reads an integer from the input source, retrying until a valid integer is entered.
     * Utilizes the getInput method to handle retries and validation.
     *
     * @return A valid integer value provided by the user.
     */
    public static int getIntegerIn() {
        return getInput(scan::nextInt, "Invalid input. Please enter a valid number: ");
    }

    /**
     * Reads a long from the input source, retrying until a valid long value is entered.
     * Utilizes the getInput method to handle retries and validation.
     *
     * @return A valid long value provided by the user.
     */
    public static long getLongIn() {
        return getInput(scan::nextLong, "Invalid input. Please enter a valid number: ");
    }

    /**
     * Reads a string from the input source, removes any leading or trailing whitespace,
     * and converts it to lowercase. This method is useful for standardizing user input.
     *
     * @return A lowercased, trimmed string provided by the user.
     */
    public static String getLowerCaseString() {
        String str = scan.next();
        return str.toLowerCase().strip();
    }

    /**
     * Closes the Scanner instance. This method should be called once the program
     * no longer requires user input. Closing the scanner is a good practice to
     * release resources associated with the input stream.
     */
    public static void closeScanner() {
        scan.close();
    }
}
