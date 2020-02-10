package InterviewPractice;

import java.util.*;
import java.util.stream.Collectors;

public class AscendingOrder {

	// Solution For Dish Question.
	private static void printAllSubsets(int[] arr) {

		List<List<Integer>> listsOfAllSets = new ArrayList<>();

		for (int i = 0; i < (1 << arr.length); i++) {
			List<Integer> set = new ArrayList<>();
			for (int j = 0; j < arr.length; j++) {
				if ((i & (1 << j)) > 0) {
					set.add(arr[j]);
				}
				listsOfAllSets.add(set);
			}
		}

/*		List<Integer> li = listsOfAllSets.stream().max((list1, list2) -> {
			long sum1 = list1.stream().map(a -> a * list1.indexOf(a)).mapToInt(Integer::intValue).sum();
			long sum2 = list2.stream().map(a -> a * list2.indexOf(a)).mapToInt(Integer::intValue).sum();
			return Long.compare(sum1, sum2);
		}).get();*/

		int max = Integer.MIN_VALUE;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (List<Integer> lli : listsOfAllSets){
			int sum = 0;
			for (int i = 0; i < lli.size(); i++) {
				sum = sum + (i+1) * lli.get(i);
			}
			map.put(sum, lli);

			if (sum > max){
				max = sum;
			}
		}


		System.out.println(max);
		System.out.println(map.get(max));
	}



	public static void main(String[] args) {

		printAllSubsets(new int[]{-9, 7, -10, -11, 0, 5});
		printAllSubsets(new int[]{-1, -9, 0, 5, -7});
		printAllSubsets(new int[]{-1, 3, 4});
		IntSummaryStatistics intSummaryStatistics = Arrays.stream(new int[]{-9, 7, -10, -11, 0, 5}).boxed().collect(Collectors.toList()).stream().mapToInt(Integer::intValue).summaryStatistics();
		System.out.println(intSummaryStatistics.getAverage());
		double d = Arrays.stream(new int[]{-9, 7, -10, -11, 0, 5}).boxed().collect(Collectors.toList()).stream().mapToInt(Integer::intValue).average().getAsDouble();
/*
[-9, 7, 0, 5]
[-1, 0, 5]
-3.0
 */
	}
}
