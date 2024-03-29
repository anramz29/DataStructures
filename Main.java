import java.util.Random;
import java.util.Scanner;

public class Main {
    enum RockPaperScissors {SCISSORS, ROCK, PAPER;}

    public static void main(String[] args) {
        // Initialize Random and Scanner
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter rock, paper, or scissors (or quit to exit): ");

            // Generate a random move for the computer
            int randomMove = random.nextInt(3);
            RockPaperScissors computerMove = RockPaperScissors.values()[randomMove];

            // Get and parse the user's input, make it upper case for enum formatting
            String userInput = scanner.nextLine().toUpperCase();

            // Check for quit condition
            if ("QUIT".equals(userInput)) {
                System.out.println("Game ended.");
                break;
            }

            // Try to parse the user's move; catch exception if input is invalid
            RockPaperScissors userMove;
            try {
                userMove = RockPaperScissors.valueOf(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter rock, paper, or scissors.");
                continue;
            }

            // Print Computer and users move
            System.out.println("Computer's move: " + computerMove);
            System.out.println("Your move: " + userMove);

            // Determine the winner
            if (userMove == computerMove) {
                System.out.println("It's a tie!");
            } else if ((userMove == RockPaperScissors.ROCK && computerMove == RockPaperScissors.SCISSORS) ||
                    (userMove == RockPaperScissors.PAPER && computerMove == RockPaperScissors.ROCK) ||
                    (userMove == RockPaperScissors.SCISSORS && computerMove == RockPaperScissors.PAPER)) {
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }
            System.out.println();
        }
        scanner.close();
    }
}
