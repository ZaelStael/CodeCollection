import java.util.*;
import java.io.*;

public class Assign5 {
	public static int indx = 0;

	public static void main(String args[]) throws FileNotFoundException {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			System.out.println("Please enter the name of the file:");
			String fileName = input.nextLine();

			try {
				File fileReader = new File(fileName);
				Scanner myReader = new Scanner(fileReader);
				while (myReader.hasNextInt()) {
					int data = myReader.nextInt();
					nums.add(data);
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");

			}
		}

		int n = nums.get(0);
		nums.remove(0);

		sum(nums);

	}

	public static int sum(ArrayList<Integer> nums) {
		int sZ = nums.size();
		int[] values = new int[sZ];
		int high = 0;
		int highC = 1;

		for (int i = 0; i < sZ; i++) {
			values[i] = nums.get(i);
			if (values[i] > high) {
				high = values[i];

			} else if (values[i] == high) {
				highC++;
			}
		}

		int[] sums = new int[sZ];
		int sum = 0;
		int tmp = 1;
		int m = 0;
		int[] max = new int[sZ];
		int[] ind = new int[sZ];
		max[0] = 0;

		ArrayList<Integer> maxs = new ArrayList<>();

		for (int i = 0; i < sZ; i++) {
			tmp = values[i];
			sum = values[i];
			for (int j = (i + 1); j < sZ; j++) {
				if (tmp <= values[j]) {
					if (j == (i + 1)) {
						sum = tmp + values[j];
						tmp = values[j];
					} else {
						sum += values[j];
						tmp = values[j];
					}

				}

				if (sum > m) {
					m = sum;
					if (!maxs.contains(sum)) {
						maxs.add(m);
						indx = j;

						if (values[j] == high && highC == 1) {
							tmp = values[i];
							sum = values[i];
						}
						if (values[j] == high && highC > 1) {
							highC--;
						}
					}
				}

			}
		}

		System.out.println(m + " @ " + indx);

		return m;

	}

	class Node {
		int data;
		Node last;
		Node next;

		Node(int d) {
			data = d;
			last = null;
			next = null;
		}
	}

	public void printList(Node hd) {
		Node tnode = hd;
		while (tnode != null) {
			System.out.print(tnode.data + " ");
			tnode = tnode.next;
		}
	}

	public static int[] sumList(ArrayList<Integer> nums) {
		int sZ = nums.size();
		int[] values = new int[sZ];
		int high = 0;
		int highC = 1;

		for (int i = 0; i < sZ; i++) {
			values[i] = nums.get(i);
			if (values[i] > high) {
				high = values[i];

			} else if (values[i] == high) {
				highC++;
			}
		}

		int[] sums = new int[sZ];
		int sum = 0;
		int tmp = 1;
		int m = 0;
		int[] max = new int[sZ];
		int count = 0;
		int finC = 0;
		max[0] = 0;

		ArrayList<Integer> maxs = new ArrayList<>();

		for (int i = 0; i < sZ; i++) {
			tmp = values[i];
			sum = values[i];
			count = 0;
			for (int j = (i + 1); j < sZ; j++) {
				if (tmp <= values[j]) {
					if (j == (i + 1)) {
						sum = tmp + values[j];
						tmp = values[j];
						count++;
					} else {
						sum += values[j];
						tmp = values[j];
						count++;
					}

				}

				if (sum > m) {
					m = sum;

					if (!maxs.contains(sum)) {
						maxs.add(m);
						indx = j;

						if (values[j] == high && highC == 1) {
							tmp = values[i];
							sum = values[i];
						}
						if (values[j] == high && highC > 1) {
							highC--;
						}
					}

					finC = count;

				}

			}

		}

		for (int z = 0; z < sZ; z++) {
			System.out.println(max);
		}

		return max;

	}
}
