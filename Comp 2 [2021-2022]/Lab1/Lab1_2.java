package Projects.Labs;

import java.util.*;

public class Lab1_2 {     //Can you write in a comment on the assignment how to fix the doubling up during the input validation?
	public static void main (String[] args) {
		
		Scanner input = new Scanner(System.in);

		System.out.println("Do you want to play?");
		String ans = input.nextLine();
		int n = 0;
		String c = " ";

		if(ans.equalsIgnoreCase("Y")){
		    
		    

			System.out.println("How many... pieces would you like in the first and last rows? I think I can do somewhere from 5-20.");
			input.reset();
			
			while (!(input.hasNextInt() || input.nextInt() < 5 || input.nextInt() > 21) ) {
			    input.reset();
				System.out.print("I can only use numbers. 5 through 21 please. You may have to say it a few times... my ears aren't what they used to be...");
				input.nextLine();
				System.out.print ("?");
			}
			n = input.nextInt();
			
			input.nextLine();

			System.out.println("How do you want those pieces to look? What 'character' would you like to use?");
			c = input.nextLine();

			printBox(n, c);

		} else if (ans.equalsIgnoreCase("N")){
			System.out.println("Did the voices tell you to hurt me?");
			System.exit(0);

		} else {
			System.out.println("Pardon? Please Start again...");
			System.exit(0);
		}
		
	}
	
	public static void printBox(int n, String c) {

		for (int i = 0; i < n; i++){
			
			if (i == 0 || i == (n-1)) {
				for (int j = 0; j < n; j++) {
					System.out.print(c);
				}
				System.out.println();
			} else {
				for (int j = 0; j < n; j++) {
					if (j == 0 || j == (n-1)) {
						System.out.print(c);
					} else {
					    System.out.print(" ");
					}

				}
				System.out.println();
			}
		}

		
		
	}
}