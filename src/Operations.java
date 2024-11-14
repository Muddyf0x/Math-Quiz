import java.util.function.BiFunction;

public class Operations {
    public static final BiFunction<Integer, Integer, Long>[] OPERATION = new BiFunction[] {
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) a + b,               // Addition at index 0
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) a - b,               // Subtraction at index 1
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) a * b,               // Multiplication at index 2
            (BiFunction<Integer, Integer, Long>) (a, b) -> (long) (b != 0 ? a / b : 0)   // Division at index 3 (handle divide-by-zero)
    };

    public static final Character[] OPERANDS = {
            '+',
            '-',
            '*',
            '/',
    };

    public static final String[] OPERATION_TEXT = new String[]{
            "0 = Mixed operations",
            "1 = addition",
            "2 = subtraction",
            "3 = multiplication",
            "4 = integer division"
    };
}
