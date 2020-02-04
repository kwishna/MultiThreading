package InterviewPractice;

public class Factorial {

	private static int getFactorial(int i){
		int fact = 1;
		if (i==0) return fact;
		while (i >= 1){
			fact = fact*i;
			i--;
		}
		return fact;
	}

	private static int recursiveFactorial(int i) {
		if (i==0)
			return 1;
		return i * recursiveFactorial(i-1);
	}

	public static void main(String[] args) {

		System.out.println(getFactorial(5));
		System.out.println(recursiveFactorial(5));
	}
}
