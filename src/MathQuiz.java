import java.util.*;
import java.util.function.BiFunction;

public class MathQuiz {

    private static final BiFunction[] OPERATIONS = new BiFunction[] {
            (BiFunction<Integer, Integer, Integer>) (a, b) -> a + b,               // Addition at index 1
            (BiFunction<Integer, Integer, Integer>) (a, b) -> a - b,               // Subtraction at index 2
            (BiFunction<Integer, Integer, Integer>) (a, b) -> a * b,               // Multiplication at index 3
            (BiFunction<Integer, Integer, Integer>) (a, b) -> b != 0 ? a / b : 0   // Division at index 4 (handle divide-by-zero)
    };
    private static String getOperant(int operation) {
        return switch (operation) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> "?";
        };
    }

    private static final Boolean DEBUG = false;
    private static final Scanner SCAN = new Scanner(System.in);
    /*
    private static final Function<Integer, Integer>[] OPERATIONS2 = new Function[]{
            // Addition
            (Function<Integer, Integer>) (difficulty) -> {
                int a = getRandomNumber(difficulty);
                int b = getRandomNumber(difficulty);
                int result = a + b;
                if (DEBUG)
                    System.out.println(result);
                System.out.println("How much is " + a + " + " + b + "?");
                System.out.print("Enter your answer (-1 to exit):");

                int userInput = getIntegerIn();

                if (userInput == -1) {
                    return -1;
                } else if (userInput == result) {
                    return 1;
                } else {
                    return 0;
                }
            },
            // Subtraction
            (Function<Integer, Integer>) (difficulty) -> {
                int a = getRandomNumber(difficulty);
                int b = getRandomNumber(difficulty);
                if (a < b) {
                    a = a + b;
                    b = a - b;
                    a = a - b;
                }
                int result = a - b;
                if (DEBUG)
                    System.out.println(result);
                System.out.println("How much is " + a + " - " + b + "?");
                System.out.print("Enter your answer (-1 to exit):");
                int userInput = getIntegerIn();

                if (userInput == -1) {
                    return -1;
                } else if (userInput == result) {
                    return 1;
                } else {
                    return 0;
                }
            },
            // Multiplication
            (Function<Integer, Integer>) (difficulty) -> {
                int a = getRandomNumber(difficulty);
                int b = getRandomNumber(difficulty);

                while (Integer.MAX_VALUE / a <= b) {
                    a -= 100;
                }

                int result = a * b;
                if (DEBUG)
                    System.out.println(result);
                System.out.println("How much is " + a + " * " + b + "?");
                System.out.print("Enter your answer (-1 to exit):");
                int userInput = getIntegerIn();

                if (userInput == -1) {
                    return -1;
                } else if (userInput == result) {
                    return 1;
                } else {
                    return 0;
                }
            },
            // Integer division
            (Function<Integer, Integer>) (difficulty) -> {
                int a = getRandomNumber(difficulty);
                int b = getRandomNumber(difficulty);
                if (a < b) {
                    a = a + b;
                    b = a - b;
                    a = a - b;
                }
                if (b == 0)
                    b = a / 2;
                int result = a / b;
                if (DEBUG)
                    System.out.println(result);
                System.out.println("How much is " + a + " / " + b + "?");
                System.out.print("Enter your answer (-1 to exit):");
                int userInput = getIntegerIn();

                if (userInput == -1) {
                    return -1;
                } else if (userInput == result) {
                    return 1;
                } else {
                    return 0;
                }
            },
    };*/

    private static final String[] OPERATION_TEXT = new String[]{
            "0 = Mixed operations",
            "1 = addition",
            "2 = subtraction",
            "3 = multiplication",
            "4 = integer division"
    };

    public static final String[] POSITIVE_COMMENTS = {
            "Well done, keep it up!",
            "Excellent, you're on the right track!",
            "Very good, keep it up!",
            "Perfect, you've got it!"
    };
    public static final String[] NEGATIVE_COMMENTS = {
            "Not quite right, try again.",
            "Almost, but there's room for improvement.",
            "That wasn't correct, try again.",
            "Give it another go, you can do it!"
    };

    private static final int CALCULATIONS_PER_GAME = 2;
    private static final int THRESHOLD = 75;

    private static final Map<String, Boolean> YES_NO_MAP = new HashMap<>() {{
        put("yes", true);
        put("y", true);
        put("yeah", true);
        put("yup", true);
        put("true", true);   // Additional affirmatives if necessary
        put("no", false);
        put("n", false);
        put("nah", false);
        put("false", false); // Additional negatives if necessary
    }};

    public static void main(String[] args) {

        int difficulty, operationType;
        while (true) {
            difficulty = chooseDifficulty();
            if (difficulty == -1) {
                System.out.println("Bye!");
                break;
            }
            operationType = chooseOperand();
            int correctAnswers = 0;
            for (int i = 0; i < CALCULATIONS_PER_GAME; i++) {
                if (play(difficulty, operationType)) {
                    correctAnswers++;
                }
            }
            giveFeedback(correctAnswers);
        }
    }

    private static int getIntegerIn() {
        int input;
        do {
            try {
                input = SCAN.nextInt();
                return input;
            } catch (Exception e) {
                System.out.print("Please only input Numbers: ");
                SCAN.nextLine();
            }
        } while (true);
    }

    private static String getLowerCaseString() {
        String str;
        str = SCAN.nextLine();
        return str.toLowerCase().strip();
    }

    private static int chooseDifficulty() {
        int difficulty;
        do {
            System.out.print("NOTE: All operations use Integer arithmetic.\n" +
                    "Enter the difficulty level (from 1-5) or -1 to exit: ");

            difficulty = getIntegerIn();
            if (difficulty == -1 || (difficulty >= 1 && difficulty <= 5)) {
                return difficulty;
            }
            System.out.println("Number out of bounds!");
        } while (true);
    }

    private static int chooseOperand() {
        int operand;
        do {
            System.out.println("Choose the operation type to practice:");
            for (int i = 0; i < OPERATION_TEXT.length; i++) {
                System.out.println(OPERATION_TEXT[i]);
            }
            System.out.println("Enter the operation (0-" + OPERATION_TEXT.length + "):");

            operand = getIntegerIn();
            if (operand >= 0 && operand <= 4) {
                return operand;
            }
            System.out.println("Number out of bounds!");
        } while (true);
    }


        private static boolean play(int difficulty, int operationType) {
            Random random = new Random();
            int a = getRandomNumber(difficulty);
            int b = getRandomNumber(difficulty);
            int userAnswer;
            int operator = (operationType == 0) ? random.nextInt(OPERATIONS.length) + 1 : operationType;
            boolean correct = true;
            System.out.println("How much is " + a + " "+ getOperant(operator) + " " + b + "?");

            do {
                System.out.print("Enter your answer (-1 to exit):");
                userAnswer = getIntegerIn();

                if (userAnswer == -1) {
                    endProgram();
                } else if (userAnswer == (int) OPERATIONS[operator - 1].apply(a, b)) {
                    System.out.println(POSITIVE_COMMENTS[(int) (Math.random()*POSITIVE_COMMENTS.length)]);
                    return correct;
                } else {
                    correct = false;
                    System.out.println(NEGATIVE_COMMENTS[(int) (Math.random()*NEGATIVE_COMMENTS.length)]);
                }
            } while(true);
        }
    /*
    private static boolean play2(int difficulty, int operationType, Scanner scan) {
        Random random = new Random();
        int operator = (operationType == 0) ? random.nextInt(4) + 1 : operationType;
        boolean correct = true;

        do {
            switch (OPERATIONS2[operator - 1].apply(difficulty)) {
                case -1 -> endProgram();
                case 0 -> {
                    correct = false;
                }
                case 1 -> {
                    return correct;
                }
            }

        } while (true);
    }*/

    private static int getRandomNumber(int difficulty) {
        Random random = new Random();
        int min = (int) Math.pow(10, difficulty - 1);
        int max = (int) Math.pow(10, difficulty) - 1;

        return random.nextInt(max - min + 1) + min;
    }

    private static void giveFeedback(int correctAnswers) {
        int percent = (int) (correctAnswers * 100 / (long) CALCULATIONS_PER_GAME);
        System.out.println("You Scored " + percent + "%");
        if (percent < THRESHOLD) {
            System.out.println("Please ask your instructor for extra help");
        } else {
            System.out.println("Very good, maybe try a harder difficulty next");
        }
    }

    private static void endProgram() {
        String answer;
        System.out.println("Are you sure you want to exit?(y/n)");
        do {
            answer = getLowerCaseString();
            if (YES_NO_MAP.get(answer)) {
                System.out.println("See ya soon!");
                System.exit(0);
            } else if (!YES_NO_MAP.get(answer)) {
                return;
            } else {
                System.out.println("Input y/n");
            }
        } while (true);
    }
}
