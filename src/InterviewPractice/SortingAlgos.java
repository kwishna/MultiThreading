package InterviewPractice;

import java.util.Arrays;

public class SortingAlgos {

	private static int[] bubbleSort(int[] arr) {
		// https://www.youtube.com/watch?v=IwwxsnZSxqU&list=PLqaka-z03VyUM1iBq7Fl5jH5tivr9HEi1
		// https://www.programiz.com/dsa/bubble-sort
		// In Bubble Sort, We Compare Each Element With The ADJECENT Element. After One Iteration, Maximum Element Shifts To Left Most.
		// So, In Next Iteration, We Don't Compare With The Left Most As It Is Already Sorted.
		// Total No Of Comparision Required For Array Of Length 6 Is :
		// 5 Comparison In 1st Iteration + 4 In 2nd + 3 In 3rd + 2 In 4th + 1 In 5th = 16 Comparision

		for (int i = 0; i < arr.length - 1; i++) { // Assume Array Of Length 5. i Range -> 0, 4
			for (int j = 0; j < arr.length - 1 - i; j++) { // j -> 0-4, 0-3, 0-2, 0-1, 0
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}

	private static void selectionSort(int[] arr, int n) { // https://www.programiz.com/dsa/selection-sort
		/**
		 * In Bubble Sort, Max Element Reaches At The End Of The Array After 1st Interation.
		 * We Do, for i in range(n)
		 * 				for j in range(n-i)
		 * 	Compare And Swap. By End Of 1 Iteration. Last Element Becomes The Largest Element.
		 * 	In Next Iteraion, We Do Comparision Between All Except The Last Item.
		 * 	So, In Each Iteration, Last Element Gets Sorted.
		 *
		 * 	But,
		 * 	In Selection,
		 * 	We Assume & Hold First Index Is Smallest Then Compare Will Remaining, If We Find Smaller Then We Hold That Index As Smallest
		 * 	And Compare Will Remaining. After 1 Iteration Completed, Current Index In Hold Becomes The Smallest One.
		 * 	Now, We Swap With The Initially Assumed Smallest Index..
		 */
		for (int i = 0; i < n; i++) {
			int leastGuy = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[leastGuy] < arr[j]) {
					leastGuy = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[leastGuy];
			arr[leastGuy] = temp;

		}
		System.out.println(Arrays.toString(arr));
	}

	private static void insertionSort(int[] array, int n) {
		/**
		 * It Is Like Sorting A Deck Of Cards.
		 * First We Have Only One Card. So, It Is By Default Sorted. When We Get Another Card Then We Compare With The
		 * First Card. We Place That Card Accordingly. Then We Get Another Card, Then We Compare With The Order Cards,
		 * And Place Accordingly. Hence, We Sort The Every New Inserted Card.
		 */
			int size = array.length;
			for (int step = 1; step < size; step++) {
				int key = array[step];
				int j = step - 1;
				while (j >= 0 && key < array[j]) {
					array[j + 1] = array[j];
					--j;
				}
				array[j + 1] = key;
			}
		System.out.println(Arrays.toString(array));
	}

	public static void main(String[] args) {
		insertionSort(new int[]{9, 5, 4, 1, 2, 0, 34, 52, 33, 77}, 10);
	}


}
