package InterviewPractice;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class PositiveNegativeAgain {

	private static int findSumOfPositiveNegative(String characters, int[] arr){

		Map<Character, Integer> mapping = new HashMap<>();
		mapping.put('P', 1);
		mapping.put('N', -1);
		char[] charArray = characters.toCharArray();
		int sum = 0;
		for (int i = 0; i < charArray.length; i++) {
			sum = sum + (mapping.get(charArray[i]) * arr[i]);
		}
		return sum*100;
	}

	@Test
	public void testPositiveNegativeSum(){
		int i = findSumOfPositiveNegative("PNPP", new int[]{1, -2, -3, 4});
		System.out.println(i);
		Assert.assertEquals(400, i);
	}

	public static void main(String[] args) {
		System.out.println(findSumOfPositiveNegative("PNPP", new int[]{1, -2, -3, 4}));
	}
}
