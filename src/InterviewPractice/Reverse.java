package InterviewPractice;

/**
 * String & Number Palindrome.
 */
public class Reverse {


	public static String reverseUsingRecursion(String input){ // Reverse Using Recurrsion

		if((input == null) || input.isEmpty()){
			return input;
		}
		// return lastCharacter + recursion(remainings)
		return input.charAt(input.length() - 1) + reverseUsingRecursion(input.substring(0, input.length() - 1 ));
	}

	public static boolean isPalindrome(String word){

		return new StringBuilder(word).reverse().toString().equals(word);
	}

	public static String reverseWords(String words){

		String reversed = "";

		char[] chars = words.toCharArray();
		for (int i = chars.length-1; i>=0; i--) {
			reversed = reversed + chars[i];
		}

		return reversed;
	}

	public static int reverserNumber(int num){

		int reverse = 0;
		int remainder;

		while (num != 0){
			remainder = num % 10;
			reverse = reverse * 10 + remainder;
			num = num / 10;
		}
		return reverse;
	}

	public static void main(String[] args) {

		String name = "malayalam";
		System.out.println(isPalindrome(name)); // true
		System.out.println(reverseUsingRecursion("Krishna")); // anhsirK
		System.out.println(reverseWords("Krishna Kumar Singh Is A Failure")); // eruliaF A sI hgniS ramuK anhsirK
		System.out.println(reverserNumber(123456789)); // 987654321
	}
}
