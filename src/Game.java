import java.util.Random;

public class Game {

    public void start() {
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

    private static int getOperationType() {
        System.out.println("Choose the operation type to practice:");
        for (int i = 0; i < Operations.OPERATION_TEXT.length; i++) {
            System.out.println(Operations.OPERATION_TEXT[i]);
        }
        System.out.print("Enter the operation (0-" + Operations.OPERATION_TEXT.length + "): ");
        while (true) {
            int operationType = Input.getIntegerIn();
            if (operationType >= 0 && operationType < Operations.OPERATION_TEXT.length)
                return operationType;
            System.out.print("Enter the operation (0-" + Operations.OPERATION_TEXT.length + "): ");
        }
    }

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

    private static void giveFeedback(int correctAnswers) {
        int percent = (int) (correctAnswers * 100 / (long) Constants.CALCULATIONS_PER_GAME);
        System.out.println("You Scored " + percent + "%");
        if (percent < Constants.THRESHOLD) {
            System.out.println("Please ask your instructor for extra help");
        } else {
            System.out.println("Very good, maybe try a harder difficulty next");
        }
    }

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
