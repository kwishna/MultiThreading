package InterviewPractice;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CarRacingAgain {

	private static int maxNumberOfRacers(int x, int[][] arr) {

		Map<Integer, Integer> countForEachStop = new HashMap<>();
		Map<Integer, Integer> countForLast = new HashMap<>();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < x; i++) {

			int firstIndex = arr[i][0];
			int lastIndex = arr[i][arr[i].length - 1];

			if (lastIndex > firstIndex) {

				for (int j = firstIndex; j <= lastIndex; j++) {

					if(countForEachStop.containsKey(j)){
						int y = countForEachStop.get(j);
						countForEachStop.put(j, y+1);
					}
					else {
						countForEachStop.put(j, 1);
					}

					if (j == lastIndex){ // After Copy Paste. Take Care!
						if(countForLast.containsKey(j)){
							int y = countForLast.get(j);
							countForLast.put(j, y+1);
						}

						else {
							countForLast.put(j, 1);
						}
					}
				}
			}
		}

		for (int i : countForEachStop.keySet()){

			if (countForLast.containsKey(i)){
				int valueFromLast = countForLast.get(i);
				int valueFromAll = countForEachStop.get(i);
				countForEachStop.put(i, valueFromAll-valueFromLast);
			}

			if (max < countForEachStop.get(i)){
				max = countForEachStop.get(i);
			}
		}

		return max;
	}

	@Test
	public void verifyResult(){
		int i = CarRacingAgain.maxNumberOfRacers(4, new int[][]{{1,3}, {2, 5}, {2, 4}, {3, 5}});
		Assert.assertEquals(3, i);
//		int i = CarRacingAgain.maxNumberOfRacers(5, new int[][]{{1, 7}, {2, 4},{6, 9}, {3, 8}, {5, 10}});
		System.out.println(i);
//		Assert.assertEquals(4, i);
	}
}
