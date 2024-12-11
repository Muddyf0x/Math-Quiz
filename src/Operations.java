import java.util.function.BiFunction;

/// The Operations class provides a set of arithmetic operations, operand symbols,
/// and descriptions for MathQuiz. Each operation is defined as a BiFunction that
/// takes two Integer arguments and returns a Long result to handle larger
/// results without overflow.
/// This class also includes symbols and descriptions for each operation type.

public class Operations {
    /// Array of arithmetic operations represented as BiFunction instances.
    /// Each BiFunction performs a specific operation on two Integer inputs and returns a Long result.
    /// - Index 0: Addition
    /// - Index 1: Subtraction
    /// - Index 2: Multiplication
    /// - Index 3: Integer Division (returns 0 if divisor is zero)
    /// - Index 4: Modulo
    public static final BiFunction<Integer, Integer, Long>[] OPERATION = new BiFunction[] {
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) a + b,               // Addition at index 0
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) a - b,               // Subtraction at index 1
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) a * b,               // Multiplication at index 2
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) (b != 0 ? a / b : 0), // Division at index 3, with divide-by-zero check
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) a % b
    };

    /// Array of characters representing the arithmetic symbols for each operation.
    /// Used to display the appropriate symbol when showing the operation to the user.
    /// - Index 0: '+'
    /// - Index 1: '-'
    /// - Index 2: '*'
    /// - Index 3: '/'
    /// - Index 4: '%'
    public static final Character[] OPERANDS = {
            '+',
            '-',
            '*',
            '/',
            '%',
    };

    /// Array of descriptive strings for each operation type.
    /// Primarily used to display options in a menu or to inform users of the available operations.
    /// - Index 0: "0 = Mixed operations"
    /// - Index 1: "1 = addition"
    /// - Index 2: "2 = subtraction"
    /// - Index 3: "3 = multiplication"
    /// - Index 4: "4 = integer division"
    /// - Index 5: "5 = modulo"
    public static final String[] OPERATION_TEXT = new String[]{
            "0 = Mixed operations",
            "1 = addition",
            "2 = subtraction",
            "3 = multiplication",
            "4 = integer division",
            "5 = modulo"
    };
}
