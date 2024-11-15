# Math Quiz Game

A command-line math quiz game designed to challenge and improve your arithmetic skills. Players can select difficulty levels and choose specific operations to practice. The program provides immediate feedback and tracks performance.

## Features

- **Multiple Difficulty Levels**: Choose from levels 1 to 5 to adjust the range of numbers used in questions.
- **Operation Types**: Practice addition, subtraction, multiplication, and division. You can also select mixed operations for variety.
- **Immediate Feedback**: Get positive or constructive feedback after each question.
- **Customizable Inputs**: Uses a flexible input system that supports different input sources (e.g., `System.in` or file-based inputs for testing).
- **Error Handling**: Robust input validation ensures the game handles invalid inputs gracefully.
- **Extensibility**: Modular design makes it easy to add new operations or customize the game logic.

## How to Run

### Prerequisites

- Java Development Kit (JDK) 17 or higher.
- A terminal or IDE to run the program.

###### Steps

1. Clone the repository:
```
git clone https://github.com/Muddyf0x/Math-Quiz.git
cd math-quiz-game
```
2. Compile the program:
```
javac *.java
```
3. Run the program:
```
java MathQuiz
```
## Project Structure

The project is organized into several classes, each handling specific functionality:

- **`MathQuiz`**: The main entry point of the program.
- **`Game`**: Manages the game flow, including input handling, question generation, and feedback.
- **`Input`**: Provides utility methods for validated user input (e.g., integers, longs, and strings).
- **`Operations`**: Defines arithmetic operations and their symbolic representations.
- **`Constants`**: Stores game-wide constants, such as feedback messages, thresholds, and input source.

## Sample Gameplay
```
Welcome to the Math Quiz Game!

NOTE: All operations use Integer arithmetic.
Enter difficulty level (1-5) or -1 to exit: 3
Choose an operation type:
0 = Mixed operations
1 = addition
2 = subtraction
3 = multiplication
4 = integer division
Enter your choice: 1

How much is 123 + 456?
Enter your answer (-1 to exit): 579
Well done, keep it up!

You scored 100%.
Very good! Try a harder difficulty next time.
```
## Configuration

- Modify **Constants** to adjust the game settings:
    - `CALCULATIONS_PER_GAME`: Number of questions per round.
    - `THRESHOLD`: Score percentage required to pass.
    - `POSITIVE_COMMENTS` and `NEGATIVE_COMMENTS`: Customize feedback messages.
    - `INPUT_SOURCE`: Change the input source (e.g., `System.in` for standard input or a file for testing).

## Contributing

Contributions are welcome! If you'd like to improve the game or fix any issues:

1. Fork the repository.
2. Create a new branch:
```
git checkout -b feature-name
```
3. Commit your changes:
```
git commit -m "Add feature-name"
```
4. Push to your branch:
```
git push origin feature-name
Open a pull request.
```
## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
