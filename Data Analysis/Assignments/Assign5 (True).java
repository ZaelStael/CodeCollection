package pkg;

import java.util.*;
import java.io.*;

public class Assign5 {
	public static void main(String args[]) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		Scanner input = new Scanner(System.in);

		// Prompt the user for the file name
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println("Please enter the name of the file:");
		String fileName = input.nextLine();

		try {
			// Read integers from the specified file
			File fileReader = new File(fileName);
			Scanner myReader = new Scanner(fileReader);
			while (myReader.hasNextInt()) {
				int data = myReader.nextInt();
				nums.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			// Handle file not found exception
			System.out.println("File not found");
			e.printStackTrace();
		}
		input.close();

		// Extract the number of elements (n) and remove it from the list
		int n = nums.get(0);
		nums.remove(0);

		// debug lines
		// System.out.println(n);
		// System.out.println(nums);

		// Calculate the maximum increasing subsequence sum
		sum(n, nums);
	}

	public static void sum(int n, ArrayList<Integer> nums) {
		int[] a = new int[n];
		int[] dp = new int[n];
		int[] prev_index = new int[n];

		// Initialize arrays with input values
		for (int i = 0; i < n; i++) {
			a[i] = nums.get(i);
			dp[i] = nums.get(i);
			prev_index[i] = -1;
		}

		int maxSumIndex = 0;
		int maxSum = dp[0];

		// Dynamic programming approach to find maximum sum
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] >= a[j] && dp[i] < dp[j] + a[i]) {

					dp[i] = dp[j] + a[i];
					prev_index[i] = j;
				}
			}

			if (dp[i] > maxSum) {
				maxSum = dp[i];
				maxSumIndex = i;
			}
		}

		// Reconstruct the subsequence
		ArrayList<Integer> subsequence = new ArrayList<>();
		int currentIndex = maxSumIndex;

		while (currentIndex != -1) {
			subsequence.add(a[currentIndex]);
			currentIndex = prev_index[currentIndex];
		}
		Collections.reverse(subsequence);

		// Print the result
		System.out.println("Maximum possible sum of an increasing subsequence: " + maxSum);
		System.out.println("Increasing subsequence with that sum: " + subsequence);

	}
}
