package InterviewPractice;

//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;

public class Rextester
{

	public static int[] meanMedian(int n, int[] arr){

		List<List<Integer>> allSetsOfList = new ArrayList<>();

//		for (int i = 0; i < (1 << n); i++) {
//			List<Double> eachSet = new ArrayList<>();
//			for (int j = 0; j < n; j++) {
//				if((i & (1 << j)) > 0){
//					eachSet.add(arr[j]); // Here, j Only
//				}
//				listOfAllSubsets.add(eachSet);
//			}
//		}

		for(int i=0; i<(1 << n); i++){

			List<Integer> sets = new ArrayList<>();

			for(int j=0; j<n; j++){

				if((i & (1 << j)) > 0){

					sets.add(arr[j]);
				}
			}
			allSetsOfList.add(sets);
		}

		Map<List<Integer>, Double> mapping = new HashMap<>();
		double mean = 0;
		double median = 0;
		double max = Double.MIN_VALUE;

		for(List<Integer> set : allSetsOfList){

			mean = set.stream().mapToInt(Integer::intValue).average().orElse(0);
			int size = set.size();
			for(int i=0; i<size; i++){

				if(set.size() % 2 == 0){//1, 2, 3, 4
					List<Integer> sortedArr = new ArrayList<>(set);
					Collections.sort(sortedArr);
					int mid = sortedArr.size()/2;
					median = (sortedArr.get(mid) + sortedArr.get(mid-1) ) / 2.0;
				}
				else{
					median = set.get(size/2);

				}

				double diff = mean - median;
				mapping.put(set, diff);

				if(max < diff){
					max = diff;
				}
			}
		}

		List<Integer> result = null;

		for(List<Integer> lis : mapping.keySet()){

			Double val = mapping.get(lis);
			if(val == max){
				result = lis;
				break;
			}
		}

		result.stream().mapToInt(Integer::intValue).toArray();

		int[] a = new int[result.size()];
		for(int i=0; i<result.size(); i++){
			a[i] = result.get(i);
		}

		return result.stream().mapToInt(Integer::intValue).toArray();
	}


	public static void main(String args[])
	{
		System.out.println(Arrays.toString(meanMedian(5, new int[]{1, 2, 2, 3, 3})));
	}
}
