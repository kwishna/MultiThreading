package InterviewPractice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		for (int i = 0; i < word.length()/2; i++) {
			if (i < j) {
				int frontChar = word.charAt(i);
				int endChar = word.charAt(--j);
				c[j] = (char) frontChar;
				c[i] = (char) endChar;
			}
		}
		return new String(c);
	}

	public static void printAllpermutation(String input){
		permutation("", input);
	}

	private static void permutation(String perm, String word) {
		if (word.isEmpty()) {
			System.err.println(perm + word);
		} else {
			for (int i = 0; i < word.length(); i++) {
				String text1 = perm + word.charAt(i);
				String text2 = word.substring(0, i) + word.substring(i + 1, word.length());
				permutation(text1, text2);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(isAnagram("krishna", "rikhans"));
		System.out.println(removeDuplicates("krisdfsdfsdfsdfashna"));
		System.out.println(reverseStringOnPlace("My Name Is Krishna Kumar Singh"));
		printAllpermutation("1234");
	}


}
