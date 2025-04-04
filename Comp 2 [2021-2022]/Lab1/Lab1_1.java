package Projects.Labs;

import java.util.*;

public class Lab1_1 {
	public static void main(String[] args) {

		int gameState = 1;
		int count = 0;

		while (gameState == 1) {

			Scanner input = new Scanner(System.in);
			int actualNum = 23;
			int firstDigit = 23 / 10;
			int lastDigit = 23 % 10;

			System.out.println(
					"We're going to play a game... You guess what number I'm thinking of. If you get it right, you live. If you fail though... you still live. My number has two digits...");
			System.out.println("What's your guess?");
			count++;

			while (!input.hasNextInt()) {
				System.out.print("A NUMBER please.");
				input.nextLine();
			}
			int guessNum = input.nextInt();

			int guessDigit1 = guessNum / 10;
			int guessDigit2 = guessNum % 10;

			if (guessDigit1 == firstDigit && guessDigit2 == lastDigit) {
				System.out.println("You have tried " + count + " times.");
				System.out.println("Congrats m8, you win.");
				gameState = 0;
			} else if (guessDigit1 != firstDigit && guessDigit2 != lastDigit) {
				System.out.println("You have tried " + count + " times.");
				System.out.println("Neither of your digits were correct.");
			} else if (guessDigit1 == firstDigit) {
				System.out.println("You have tried " + count + " times.");
				System.out.println("Your tens place was correct.");
			} else if (guessDigit2 == lastDigit) {
				System.out.println("You have tried " + count + " times.");
				System.out.println("Your ones place was correct.");
			}

		}
	}
}