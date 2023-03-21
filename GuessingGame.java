
import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;
        int numGuesses = 0;
        int guess;
        boolean win = false;

        System.out.println("I'm thinking of a number between 1 and 100. Can you guess what it is?");

        while (!win) {
            System.out.print("Enter your guess: ");
            guess = input.nextInt();
            numGuesses++;

            if (guess == secretNumber) {
                win = true;
            } else if (guess < secretNumber) {
                System.out.println("Too low. Guess again.");
            } else {
                System.out.println("Too high. Guess again.");
            }
        }

        System.out.println("Congratulations! You guessed the number in " + numGuesses + " guesses.");
    }
}
