package InterviewPractice;

import java.util.*;

public class MeanMinusMedian {

	// Solution For Car Race
	public static int fun(int matrix[][],int n,int m){
		int[] points = new int[n*2];
		int last_stop=0,k=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				points[k++] = matrix[i][j];
				if(last_stop < matrix[i][j])
					last_stop = matrix[i][j];
			}
		}
//		System.out.println("last_stop"+last_stop);
		int max_stops=0,count;
		for(int i=1;i<last_stop;i++){
			count=0;
			for(int j=0;j<m*n;j=j+2){
				if(i>=points[j] && i<points[j+1]){
					count++;
				}
			}
//			System.out.println(count);
			if(max_stops<count){
				max_stops = count;
			}
		}
		return max_stops;
	}

	// Solution For Car Race
	private static void printTheMax(int[][] arr){

		int outArrLength = arr.length;

		Map<Integer, Integer> mapping = new HashMap<>();
		Map<Integer, Integer> allEndPoints = new HashMap<>();
		for(int i=0; i<outArrLength; i++){// 0, 3

			int begin = arr[i][0];//1
			int last = arr[i][arr[i].length-1];

			for(int j=begin; j<=last; j++){

				if(mapping.containsKey(j)){
					mapping.put(j, mapping.get(j)+1);
				}
				else{
					mapping.put(j, 1);
				}

				if(j==last){
					if(allEndPoints.containsKey(j)){
						allEndPoints.put(j, mapping.get(j)+1);
					}
					else{
						allEndPoints.put(j, 1);
					}
				}
			}
		}

		System.out.println(mapping);
		System.out.println(allEndPoints);
		int max = Integer.MIN_VALUE;
		for (int k : allEndPoints.keySet()){
			int valueEnd = allEndPoints.get(k);

			int value = mapping.get(k);
			mapping.put(k, value-valueEnd);
		}

		for (int k : mapping.values()){
			if (k > max){
				max = k;
			}
		}
		System.out.println(max);
		System.out.println(mapping);
	}

	// Solution For Mean-Median
	private static void printAllSubsets(int[] arr) {

		List<List<Integer>> listsOfAllSets = new ArrayList<>();

		// To Print All Possible Sets
		for (int i = 0; i < (1 << arr.length); i++) {
			List<Integer> set = new ArrayList<>();
			for (int j = 0; j < arr.length; j++) {
				if ((i & (1 << j)) > 0) {
					set.add(arr[j]);
				}
				listsOfAllSets.add(set); // List Of All Possible Lists
			}
		}
//		System.out.println(listsOfAllSets);

		double max = Double.MIN_VALUE;
		Map<Double, List<Integer>> map = new HashMap<>();
		for (List<Integer> lis : listsOfAllSets) { // 1, 2, 3, 4, 5
			if (lis.size() > 2) {

				double median = 0;
				List<Integer> sortedList = new ArrayList<>(lis);
				sortedList.sort(Comparator.naturalOrder());
				int listSize = sortedList.size();
				if (listSize % 2 == 0) {
					int sumOfTwoMiddle = sortedList.get(listSize / 2) + sortedList.get((listSize / 2) - 1);
					median = sumOfTwoMiddle / 2.0;
				} else {
					median = sortedList.get(listSize / 2);
				}

				double average = sortedList.stream().mapToInt(Integer::intValue).average().getAsDouble();

				double diffBetweenMeanMedian = average - median;

				if (diffBetweenMeanMedian > max) {
					max = Math.abs(diffBetweenMeanMedian);
				}

				map.put(diffBetweenMeanMedian, lis);
			}
		}
		System.out.println(max);
		System.out.println(Arrays.toString(map.get(max).toArray(new Integer[0])));
	}

	public static void main(String[] args) {
//		printAllSubsets(new int[]{1, 2, 2, 3, 3}); // 2.5, 2.5, {1, 2, 4} => 2.333, 2
//		printAllSubsets(new int[]{1, 2, 2, 3, 3});
//		printTheMax(new int[][]{{1, 3}, {2, 5},{2, 4}, {3, 5}});
//		printTheMax(new int[][]{{1, 7}, {2, 4},{6, 9}, {3, 8}, {5, 10}});
		System.out.println(fun(new int[][]{{1, 2}, {4, 5},{6, 9}, {3, 8}}, 4, 2));
		printTheMax(new int[][]{{1, 2}, {4, 5}, {6, 9}, {3, 8}});
	}
}