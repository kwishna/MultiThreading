package InterviewPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	private static void printAllSubArrays(int[] arr) {
		int arrLength = arr.length;
		ArrayList<Integer> list;
		for (int i = 0; i < (1 << arrLength); i++) {
			list = new ArrayList<>();
			for (int j = 0; j < arrLength; j++) {
				if ((i & (1 << j)) > 0) {
					list.add(arr[j]);
				}
			}
//			permutingArray(list, 0);
			int[] aa = list.stream().mapToInt(Integer::intValue).toArray();
			System.out.println(Arrays.toString(aa));
		}
	}

	private static void permutingArray(java.util.List<Integer> arrayList, int element) {
		for (int i = element; i < arrayList.size(); i++) {
			Collections.swap(arrayList, i, element);
			permutingArray(arrayList, element + 1);
			Collections.swap(arrayList, element, i);
		}
		if (element == arrayList.size() - 1) {
			System.out.println(java.util.Arrays.toString(arrayList.toArray()));
		}
	}

	public static void main(String[] args) {
		System.out.println(System.nanoTime());
//		System.arraycopy();
		// Stream.of(arr1, arr2).flatMap(Stream::of).toArray();
		int[] arr = {1, 2, 3};
		// printAllSubArrays(arr);
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		permutingArray(list, 0);
		System.out.println(Arrays.equals(list.stream().mapToInt(Integer::intValue).toArray(), arr));
		List<Integer> list1 = Arrays.stream(arr).boxed().collect(Collectors.toList());
		int[][] ar = {{1,3,5}, {4,6,8}, {6,8,10}};

	}
}
