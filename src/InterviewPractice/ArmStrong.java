package InterviewPractice;

/**
 * Is Armstrong?
 */
public class ArmStrong {

	private static int cube(int n){
		return n*n*n;
	}

	private static boolean isArmstrong(int value){

		int number = value;
		int armstrong = 0;
		int remainder;

		while (number != 0){
			remainder = number%10;
			armstrong = armstrong + cube(remainder);
			number = number/10;
		}
		return value == armstrong;
	}

	public static void main(String[] args) {
		System.out.println(isArmstrong(153));
	}
}
