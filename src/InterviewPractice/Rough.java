package InterviewPractice;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Rough {

	@Override
	public boolean equals(Object obj) {
		return this == (Rough) obj;
	}

	public static boolean isArmStrong(int num){
		int len = Integer.toString(num).length();
		int k = num;
		int sum = 0;
		while (k > 0) {
			int remainder = k % 10;
			k = k / 10;
			sum = (int) (sum + Math.pow(remainder, len));
		}

		return sum == num;
	}

	public static void getArmStrongNumber()	{
		// Print 1st 3 Digit Armstrong Number
		for (int i = 100; i < 1000; i++) {

			int sum = 0;
			int k = i;
			while (k > 0) {
				int remainder = k % 10;
				k = k / 10;
				sum = (int) (sum + Math.pow(remainder, 3));
			}

			if (sum == i) {
				System.out.println(sum);
				break;
			}
		}
	}

	public static long printReverse(long num){

		long result = 0;

		while (num>0){
			long remainder = num % 10;
			num = num / 10;
			result = result*10 + remainder;
		}
		return result;
	}

	public static boolean isPrime(int n) {
		boolean isPrime = false;

		if (n == 3 || n == 2) isPrime = true;
		else if (n == 0 || n == 1) isPrime = false;
		else {
			for (int i = 2; i <= n / 2; i++) {
				if (n % i == 0) {
					isPrime = false;
					break;
				} else isPrime = true;
			}
		}
		return isPrime;
	}

	public static int[] sortAscending(int[] arr) { // Bubble Sort
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i; j < arr.length; j++) {
					if (arr[i] > arr[j]) {
						arr[i] = arr[i] + arr[j];
						arr[j] = arr[i] - arr[j];
						arr[i] = arr[i] - arr[j];
					}
			}
		}
		return arr;
	}

	public static void maxValue(int[] arr) {
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;

		for (int j : arr) {
			if (max1 < j) {
				max3 = max2;
				max2 = max1;
				max1 = j;
			} else if (max2 < j) {
				max3 = max2;
				max2 = j;
			} else if (max3 < j) {
				max3 = j;
			}
		}
		System.out.printf("Values Are: %d, %d, %d ", max1, max2, max3);
	}

	public static void main(String[] args) {
		int[] arr = { 64, 34, 25, 12, 22, 11, 90 };
		maxValue(arr);
		Optional<Integer> i = Stream.of(1, 2, 3, 4, 5).min(Comparator.naturalOrder());
		System.out.println(i.get());
		System.out.println(Stream.of(1, 2, 3, 4, 5).reduce(Integer::sum).get());

	}
}
