package InterviewPractice;

import java.util.Arrays;

public class MissingNo {

	private static int printMissingNumber(int[] arr, int count){
		int totalSumOfArr = 0;
		for (int i : arr){
			totalSumOfArr = totalSumOfArr + i;
		}
		int sumOfCounting = (count * (count + 1)) / 2;
		return sumOfCounting - totalSumOfArr;
	}

	private static void printAllMissingNumber(int[] arr, int count){

		int totalSumOfArr = 0;
		for (int i : arr){
			totalSumOfArr = totalSumOfArr + i;
		}
		int sumOfCounting = (count * (count + 1)) / 2;
		int lessCount = sumOfCounting - totalSumOfArr;
		for (int i=0; i<count-1; i++){
			for (int j=i+1; j<count; j++){
				if(i+j == lessCount && !Arrays.toString(arr).contains(i+"")){
					System.out.println(i+"--"+j);
					break;
				}
			}
		}
	}

	public static void main(String[] args) {

		int[] arr = {1, 2, 3, 5, 6, 7, 8, 9, 10};  // 15 [ 1, 2, 3, 4, 5 ]
		System.out.println(printMissingNumber(arr, 10));
		printAllMissingNumber(new int[]{1, 2, 3, 5, 6, 7, 9, 10}, 10);
	}
}
