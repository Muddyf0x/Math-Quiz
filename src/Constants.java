import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Constants {
    public static final Boolean DEBUG = false;
    public static final int CALCULATIONS_PER_GAME = 2;
    public static final int THRESHOLD = 75;
    public static final InputStream INPUT_SOURCE = System.in;
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
