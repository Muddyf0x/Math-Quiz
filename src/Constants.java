import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
/**
 * A utility class that holds constant values for application configuration and responses.
 * Contains default settings, messages, and input mappings.
 */
public class Constants {

    /**
     * Flag for enabling or disabling debug mode.
     * Set to true for debug output, false for normal operation.
     */
    public static final Boolean DEBUG = false;

    /**
     * Defines the number of calculations per game session.
     */
    public static final int CALCULATIONS_PER_GAME = 5;

    /**
     * Defines how often the player will be asked for the answer before he gets the next question.
     */
    public static final int MAXIMUM_TRIES_PER_QUESTION = 3;

    /**
     * Threshold percentage used for evaluation purposes.
     * Represents the minimum success rate to achieve a positive result.
     */
    public static final int THRESHOLD = 75;

    /**
     * Specifies the default input source for user data.
     * Uses standard input (System.in) by default.
     */
    public static final InputStream INPUT_SOURCE = System.in;

    /**
     * Array of positive feedback messages for user encouragement.
     * Used when a user achieves a correct result.
     */
    public static final String[] POSITIVE_COMMENTS = {
            "Well done, keep it up!",
            "Excellent, you're on the right track!",
            "Very good, keep it up!",
            "Perfect, you've got it!"
    };

    /**
     * Array of constructive feedback messages for user improvement.
     * Used when a user provides an incorrect result.
     */
    public static final String[] NEGATIVE_COMMENTS = {
            "Not quite right, try again.",
            "Almost, but there's room for improvement.",
            "That wasn't correct, try again.",
            "Give it another go, you can do it!"
    };

    /**
     * A map of common affirmative and negative responses to Boolean values.
     * Maps various inputs to true or false for easy validation of user responses.
     * Affirmative responses map to true (e.g., "yes", "y"), and negative responses map to false (e.g., "no", "n").
     */
    public static final Map<String, Boolean> YES_NO_MAP = new HashMap<>() {{
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
}

