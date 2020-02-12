package InterviewPractice;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MeanMediaAgain {

	public static double[] meanMedian(int n, double[] arr){

		List<List<Double>> listOfAllSubsets = new ArrayList<>();

		for (int i = 0; i < (1 << n); i++) {
			List<Double> eachSet = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if((i & (1 << j)) > 0){
					eachSet.add(arr[j]); // Here, j Only
				}
				listOfAllSubsets.add(eachSet);
			}
		}

		double max = Double.MIN_VALUE;
		Map<Double, List<Double>> mapping = new HashMap<>();

		for (List<Double> eachList : listOfAllSubsets){

			double mean = 0;
			double median = 0;

			if (eachList.size() > 0){

				mean = eachList.stream().mapToDouble(Double::doubleValue).average().getAsDouble(); // Don't Forget Double
				int sizeOfArray = eachList.size();
				if(sizeOfArray % 2 == 0){// Equal Means Even No. Complex
					median = (eachList.get(sizeOfArray/2) + eachList.get((sizeOfArray/2)-1))/2.0;
				}
				else {
					List<Double> sortedList = new ArrayList<>(eachList);
					Collections.sort(sortedList);
					median = sortedList.get(sizeOfArray/2);
				}

				double difference = mean-median; // Not Absolute Value. Mistaken!

				if (max < difference){
					max = difference;
				}
//				System.out.println("Mean - "+mean+", Median - "+median+", Difference - "+difference+", List - "+eachList);
				mapping.put(difference, eachList);
			}
		}

//		System.out.println(mapping);
		List<Double> result = null;
		for (Double d : mapping.keySet()){
			if(d == max){
				result = mapping.get(d);
			}
		}

		return result.stream().mapToDouble(Double::doubleValue).toArray();
	}

	@Test
	public void meanMedianOne(){
		double[] result = MeanMediaAgain.meanMedian(4, new double[]{1, 2, 3, 4});
		System.out.println(Arrays.toString(result));
		Assert.assertEquals(Arrays.toString(result), Arrays.toString(new int[]{1, 2, 4}));
	}

	public static void main(String[] args) {
		meanMedian(4, new double[]{1, 2, 3, 4});
		// 2.5, 1.5
	}
}
