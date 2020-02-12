package InterviewPractice;

import java.util.*;

public class AarryInt {

	public static int maximum(int[] arr) {

		int max = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;
		for (int i : arr){ // Compare With Each And Replace If Found Bigger.
			if (max < i){
				max3 = max2;
				max2 = max;
				max = i;
			}

			else if(max2 < i /* && max2 != max */){
				max3 = max2;
				max2 = i;
			}

			else if(max3 < i /* && max3 != max2 */){
				max3 = i;
			}
		}
		System.out.println(max+"-"+max2+"-"+max3);
		return max;
	}

	private static int minimun(int[] arr){
		int min = Integer.MAX_VALUE;
		for (int i : arr){
			if (min > i){
				min = i;
			}
		}
		return min;
	}

	private static int[] sortAscending(int[] arr){
//		Arrays.sort(arr);
		int arrLength = arr.length;
		for (int i = 0; i < arrLength-1; i++) {
			for (int j = i+1; j < arrLength; j++) {
				if (arr[i] > arr[j]){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}

	private static int[] sortDescending(int[] arr){
		int arrLength = arr.length;
		for (int i = 0; i < arrLength-1; i++) {
			for (int j = i+1; j < arrLength; j++) {
				if (arr[i] < arr[j]){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}

	private static void swapUsingTemp(int a, int b){
		System.out.println("Value Of a Is "+a+" & Value Of b Is "+b);
		int temp = a;
		a = b;
		b = temp;
		System.out.println("Now, Value Of a Is "+a+" & Value Of b Is "+b);
	}

	private static void swapWithoutUsingTemp(int a, int b){
		System.out.println("Value Of a Is "+a+" & Value Of b Is "+b);
		a = a + b; // 2, 3
		b = a - b;
		a = a - b;
		System.out.println("Now, Value Of a Is "+a+" & Value Of b Is "+b);
	}

	private static int[] bubbleSort(int[] arr){
		// https://www.youtube.com/watch?v=IwwxsnZSxqU&list=PLqaka-z03VyUM1iBq7Fl5jH5tivr9HEi1

		// In Bubble Sort, We Compare Each Element With The ADJECENT Element. After One Iteration, Maximum Element Shifts To Left Most.
		// So, In Next Iteration, We Don't Compare With The Left Most As It Is Already Sorted.
		// Total No Of Comparision Required For Array Of Length 6 Is :
		// 5 Comparison In 1st Iteration + 4 In 2nd + 3 In 3rd + 2 In 4th + 1 In 5th = 16 Comparision

		for (int i = 0; i < arr.length-1; i++) { // Assume Array Of Length 5. i Range -> 0, 4
			for (int j = 0; j < arr.length-1 - i; j++) { // j -> 0-4, 0-3, 0-2, 0-1, 0
				if (arr[j] > arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}

	private static void printDuplicates(int[] arr){
		int arrLength = arr.length;
		for (int i = 0; i < arrLength-1; i++) {
			for (int j = i+1; j < arrLength; j++) {
				if (arr[i] == arr[j]){
					System.out.println(arr[i]);
				}
			}
		}
	}

	private static void printDuplicatesInLessIteration(int[] arr){
		Arrays.sort(arr);
		int arrLength = arr.length;
//		int k = 0;
		for (int i = 0; i < arrLength-1; i++) {
			if(arr[i] + arr[i+1] == arr[i]*2){
//				k++;
				System.out.println("Duplicate Found : "+arr[i]);
				i++;
			}
		}
//		System.out.println(k + " - Iterations");
	}

	public static boolean isBinary(int number) {
		int copyOfInput = number;

		while (copyOfInput != 0) {
			if (copyOfInput % 10 > 1) { // Any Binary Number Like 10101010. Take Each Digit By Using Remainder & Check If Is 0 Or 1.
				return false;
			}
			copyOfInput = copyOfInput / 10;
		}
		return true;
	}

	private static int[] removeDuplicates(int[] arr){

		Set<Integer> intSet = new LinkedHashSet<>();
		for (int i : arr){
			intSet.add(i);
		}
		return intSet.stream().mapToInt(Integer::intValue).toArray();
//		return intSet.toArray();
	}

	private static int[] removeDups(int[] arr){ // If Array Is Sorted, Then Duplicate Elements Will Be Together.
												// In This Case, We Will Put Elements One By One In A Temp Array.
												// If Previous Element Inserted In Arrays Matches The To Be Inserted Element. Then Duplicate Found.
		Arrays.sort(arr);
		int arrLength = arr.length;
		int index = 0;
		int[] noDupArr = new int[arrLength];
		int justAddedToNoDup = arr[0];
		noDupArr[index] = justAddedToNoDup;

		for (int i = 1; i < arrLength; i++) {
			if (arr[i] != justAddedToNoDup){
				index++;
				noDupArr[index] = arr[i];
				justAddedToNoDup = arr[i];
			}
		}
		return noDupArr;
	}

	public static void printPairsUsingSetWhichSumMatches(int[] numbers, int sum){ // Important
		if(numbers.length < 2){
			return;
		}
		Set<Integer> set = new HashSet<>(numbers.length);
		for(int value : numbers){
			int target = sum - value;
			if(!set.contains(target)){
				set.add(value);
			}else {
				System.out.printf("(%d, %d) %n", value, target);
			}
		}
	}

	public static void printPairsUsingTwoPointers(int[] numbers, int k){
		if(numbers.length < 2){
			return;
		}
		Arrays.sort(numbers);

		int left = 0; int right = numbers.length -1;
		while(left < right){
			int sum = numbers[left] + numbers[right];
			if(sum == k){
				System.out.printf("(%d, %d) %n", numbers[left], numbers[right]);
				left = left + 1;
				right = right -1;
			}else if(sum < k){
				left = left +1;
			}else {
				right = right -1;
			}
		}
	}

	public static char firstNonRepeatedCharacter(String word) {
		HashMap<Character,Integer> scoreboard = new HashMap<>();
		// build table [char -> count]
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (scoreboard.containsKey(c)) {
				scoreboard.put(c, scoreboard.get(c) + 1);
			} else {
				scoreboard.put(c, 1);
			}
		}
		// since HashMap doesn't maintain order, going through string again
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (scoreboard.get(c) == 1) {
				return c;
			}
		}
		throw new RuntimeException("Undefined behaviour");
	}

	private static void commonIn3Arrays(int ar1[], int ar2[], int ar3[]) {
		// Initialize starting indexes for ar1[], ar2[] and ar3[]
		int i = 0, j = 0, k = 0;

		// Iterate through three arrays while all arrays have elements
		while (i < ar1.length && j < ar2.length && k < ar3.length) {
			Arrays.sort(ar1);
			Arrays.sort(ar2);
			Arrays.sort(ar3);
			// If x = y and y = z, print any of them and move ahead
			// in all arrays
			if (ar1[i] == ar2[j] && ar2[j] == ar3[k]) {
				System.out.print(ar1[i] + " ");
				i++;
				j++;
				k++;
			}

			// x < y
			else if (ar1[i] < ar2[j])
				i++;

				// y < z
			else if (ar2[j] < ar3[k])
				j++;

				// We reach here when x > y and z < y, i.e., z is smallest
			else
				k++;
		}
	}

/*

		private	static void reverse(int[] arr){
			for (int i = 0; i < arrayA.length; i++)
			{
				int other = array.length - i - 1;
 				int temp = array[i];
 				array[i] = array[other];
 				array[other] = temp;
 			}
		}

*/


	public static void main(String[] args) {

		int[] num = new int[]{3, 1, 10, 4, 1, 6, 7, 2, 9, 1, 5, 3, 4};

//		System.out.println(maximum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
//		System.out.println(minimun(new int[]{22, 44, 4, 5, 6, 7, 8, 9, 10}));
//		System.out.println(Arrays.toString(sortAscending(new int[]{3, 1, 4, 1, 6, 7, 2, 9, 0, 3,})));
//		System.out.println(Arrays.toString(sortDescending(new int[]{3, 1, 4, 1, 6, 7, 2, 9, 0, 3,})));
//		printDuplicates(num);
//		System.out.println(Arrays.toString(removeDuplicates(num)));
//		System.out.println(Arrays.toString(removeDups(num)));
//		swapUsingTemp(2, 3);
//		swapWithoutUsingTemp(2, 3);

		printPairsUsingSetWhichSumMatches(num,15);
		System.out.println(Arrays.toString(bubbleSort(num)));
		printDuplicatesInLessIteration(num);
		System.out.println(maximum(new int[]{1, 2, 3, 4, 5}));
	}
}
