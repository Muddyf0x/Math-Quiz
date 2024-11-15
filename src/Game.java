import java.util.Random;

/// The Game class manages the main logic of a math-based game, including handling user input for
/// difficulty and operation type, running rounds of arithmetic questions, and providing feedback
/// based on the player’s performance.
/// The Game class interacts with the Input class for validated user input, and uses the Operations
/// class to perform arithmetic operations. The game repeats until the player chooses to exit.
public class Game {
    /**
     * Starts the game loop, which continues until the player opts to exit.
     * Each loop iteration represents a complete game round, where the user sets the difficulty
     * and chooses an operation type. Feedback is provided based on the player's performance.
     */
    public void start() {
        System.out.println("Welcome to the Math Quiz Game!\n");
        while (true) {
            int difficulty = getDifficulty();
            if (difficulty == -1) {
                System.out.println("Bye!");
                break;
            }
            int operationType = getOperationType();
            int correctAnswers = 0;
            for (int i = 0; i < Constants.CALCULATIONS_PER_GAME; i++) {
                if (playRound(difficulty, operationType)) {
                    correctAnswers++;
                }
            }
            giveFeedback(correctAnswers);
        }
        Input.closeScanner();
    }
    /**
     * Prompts the user to select a difficulty level. If an invalid value is provided, the prompt
     * is repeated until a valid choice is made. The user can enter -1 to exit the game.
     *
     * @return The difficulty level chosen by the player (1-5) or -1 to exit.
     */
    private static int getDifficulty() {
        System.out.print("NOTE: All operations use Integer arithmetic.\n" +
                "Enter the difficulty level (from 1-5) or -1 to exit: ");
        while (true) {

            int difficulty = Input.getIntegerIn();
            if (difficulty == -1 || (difficulty >= 1 && difficulty <= 5)) {
                return difficulty;
            }
            System.out.println("Number out of bounds!");
            System.out.print("Enter the difficulty level (from 1-5) or -1 to exit: ");
        }
    }
    /**
     * Prompts the user to select an operation type, displaying the available options.
     * If an invalid option is provided, the prompt is repeated until a valid choice is made.
     *
     * @return The operation type chosen by the player (0 = mixed operations, 1-4 for specific operations).
     */
    private static int getOperationType() {
        System.out.println("Choose the operation type to practice:");
        for (int i = 0; i < Operations.OPERATION_TEXT.length; i++) {
            System.out.println(Operations.OPERATION_TEXT[i]);
        }
        System.out.print("Enter the operation (0-" + (Operations.OPERATION_TEXT.length - 1) + "): ");
        while (true) {
            int operationType = Input.getIntegerIn();
            if (operationType >= 0 && operationType < Operations.OPERATION_TEXT.length)
                return operationType;
            System.out.print("Enter the operation (0-" + (Operations.OPERATION_TEXT.length - 1) + "): ");
        }
    }
    /**
     * Runs a single round of questions based on the selected difficulty and operation type.
     *
     * @param difficulty   The difficulty level chosen by the player, affecting the range of numbers used.
     * @param operationType The operation type chosen by the player (0 for mixed, or a specific operation).
     * @return True if the question was answered correctly on the first try
     */
    private static boolean playRound(int difficulty, int operationType) {
        int a = RandomUtils.getRandomNumberWithNDigits(difficulty);
        int b = RandomUtils.getRandomNumberWithNDigits(difficulty);
        boolean correct = true;

        if (a < b) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        Random random = new Random();
        int operator = (operationType == 0) ? random.nextInt(Operations.OPERATION.length) + 1 : operationType - 1;
        long result = Operations.OPERATION[operator].apply(a, b);

        if (Constants.DEBUG)
            System.out.println("DEBUG: The result is: " + result);
        System.out.println("How much is " + a + " "+ Operations.OPERANDS[operator] + " " + b + "?");

        do {
            System.out.print("Enter your answer (-1 to exit): ");
            long userAnswer = Input.getLongIn();

            if (userAnswer == -1) {
                endGame();
            } else if (userAnswer == result) {
                System.out.println(Constants.POSITIVE_COMMENTS[(int) (Math.random()*Constants.POSITIVE_COMMENTS.length)]);
                return correct;
            } else {System.out.println(Constants.NEGATIVE_COMMENTS[(int) (Math.random()*Constants.NEGATIVE_COMMENTS.length)]);

                correct = false;
            }
        } while (true);

    }
    /**
     * Provides feedback to the user based on the number of correct answers.
     * Displays the user’s score as a percentage, and suggests improvements if necessary.
     *
     * @param correctAnswers The count of questions answered correctly in the round.
     */
    private static void giveFeedback(int correctAnswers) {
        int percent = (int) (correctAnswers * 100 / (long) Constants.CALCULATIONS_PER_GAME);
        System.out.println("You Scored " + percent + "%");
        if (percent < Constants.THRESHOLD) {
            System.out.println("Please ask your instructor for extra help");
        } else {
            System.out.println("Very good, maybe try a harder difficulty next");
        }
    }

    /**
     * Lets the User end the program after asking for conformation. The input is checked against a map of different
     * possible no/yes variants specified in Constants.YES_NO_MAP
     */
    private static void endGame() {
        String answer;
        System.out.print("Are you sure you want to exit?(y/n): ");
        do {
            answer = Input.getLowerCaseString();

            // Check if the answer exists in the map before accessing its value
            if (Constants.YES_NO_MAP.containsKey(answer)) {
                if (Constants.YES_NO_MAP.get(answer)) {
                    System.out.println("See ya soon!");
                    System.exit(0);
                } else {
                    return; // If answer is a recognized "no" response
                }
            } else {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        } while (true);
    }
}
