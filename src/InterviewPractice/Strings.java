package InterviewPractice;

import java.util.*;
import java.util.stream.Collectors;

public class Strings { // Sort An String.

	public static boolean isAnagram(String one, String two) {

		char[] arrOne = one.toCharArray();
		char[] arrTwo = two.toCharArray();
		Arrays.sort(arrOne);
		Arrays.sort(arrTwo);
		return Arrays.equals(arrOne, arrTwo);
	}

	public static String removeDuplicates(String word) {

/*		StringBuilder me = new StringBuilder();
		LinkedHashSet<Character> chars = new LinkedHashSet<>();
		word.chars().forEach(a -> chars.add((char) a));
		chars.forEach(me::append);
		return me.toString();*/

		String withoutDup = "";

		for (int i = 0; i < word.length() - 1; i++) {
			for (int j = i + 1; j < word.length(); j++) {
				if (word.charAt(i) != word.charAt(j)) {
					String one = withoutDup.contains(word.charAt(i) + "") ? "" : String.valueOf(word.charAt(i));
					withoutDup = withoutDup + one;
				}
			}
		}
		return withoutDup;

		/*
			StringBuilder builder = new StringBuilder();
			s.chars().distinct().forEach(c -> builder.append((char) c));
		 */

		/*
		Arrays.stream(s.split("")).distinct().collect(Collectors.joining(""))
		 */
	}

	public static boolean checkDuplicateUsingSet(String[] input) {
		List<String> inputList = Arrays.asList(input);
		Set<String> inputSet = new HashSet<>(inputList);
		return inputSet.size() < inputList.size();
	}

	public static boolean checkDuplicateUsingAdd(String[] input) {
		Set<String> tempSet = new HashSet<>();
		for (String str : input) {
			if (!tempSet.add(str)) {
				return true;
			}
		}
		return false;
	}

	private static String reverseStringOnPlace(String word) {
		char[] c = new char[word.length()];
		int j = word.length();
		for (int i = 0; i < word.length() / 2; i++) {
			if (i < j) {
				int frontChar = word.charAt(i);
				int endChar = word.charAt(--j);
				c[j] = (char) frontChar;
				c[i] = (char) endChar;
			}
		}
		return new String(c);
	}

	private static void permutation(String perm, String word) {
		if (word.isEmpty()) {
			System.err.println(perm + word);
		} else {
			for (int i = 0; i < word.length(); i++) {
				String text1 = perm + word.charAt(i);
				String text2 = word.substring(0, i) + word.substring(i + 1);
				permutation(text1, text2);
			}
		}
	}

	public static void main(String[] args) {
//		System.out.println(isAnagram("krishna", "rikhans"));
//		System.out.println(removeDuplicates("krisdfsdfsdfsdfashna"));
//		System.out.println(reverseStringOnPlace("My Name Is Krishna Kumar Singh"));
//		printAllpermutation("1234");
//		perm("", "ABCD");
		allSubSets("ABC".toCharArray());
		System.out.println(3 & 2);
	}


	private static void perm(String perm, String text) {

		if (text.isEmpty()) {
			System.out.println(perm + text);
		} else {
			for (int i = 0; i < text.length(); i++) {
				String part1 = perm + text.charAt(i);
				String part2 = text.substring(0, i) + text.substring(i + 1);
				perm(part1, part2);
			}
		}
	}

	/*
	All Subsets
	 */
	private static void allSubSets(char[] arr) { // All Combinations
		/**
		 * 1 << 2 = 1*2^2 = 4
		 * 1 >> 2 = 1/2^2 = 1/4
		 */
		for (int i = 0; i < (1 << arr.length); i++) {
			List<Character> l = new ArrayList<>();
			for (int j = 0; j < arr.length; j++) {
				if ((i & (1 << j)) > 0) { //if ((a < 0) && (b < 0) == if ((a & b) < 0) // Might Be
					l.add(arr[j]);
				}
			}

			perm("", l.stream().map(String::valueOf).collect(Collectors.joining()));
		}
	}

	static void permutingArray(java.util.List<Integer> arrayList, int element) {
		for (int i = element; i < arrayList.size(); i++) {
			java.util.Collections.swap(arrayList, i, element);
			permutingArray(arrayList, element + 1);
			java.util.Collections.swap(arrayList, element, i);
		}
		if (element == arrayList.size() - 1) {
			System.out.println(java.util.Arrays.toString(arrayList.toArray()));
		}
	}

}
