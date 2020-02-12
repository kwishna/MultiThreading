package InterviewPractice;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.*;

public class DishesProblemAgain {

	public static int maxCoeff(int n, int[] arr) {

		List<List<Integer>> allSetsOfList = new ArrayList<>();

		for (int i = 0; i < (1 << n); i++) {
			List<Integer> subSets = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0){
					subSets.add(arr[j]);
				}
			}
			allSetsOfList.add(subSets);
		}

		int max = Integer.MIN_VALUE;

		for (List<Integer> set : allSetsOfList){
			int coeff = 0;
			for (int i = 0; i < set.size(); i++) {
				coeff = coeff + ((i+1) * set.get(i));
			}
			if (max < coeff){
				max = coeff;
			}
		}
		return max;
	}

		@Test
	public void meanMedianOne(){
		int max = DishesProblemAgain.maxCoeff(5, new int[]{-1, -9, 0, 5, -7});
		System.out.println(max);
		Assert.assertEquals(14, max);
	}

	public static void main(String[] args) {
		System.out.println(maxCoeff(3, new int[]{-1, 3, 4}));
	}

}
