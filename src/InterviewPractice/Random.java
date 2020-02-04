package InterviewPractice;

public class Random {

	public static boolean doesLeapYear(int year){
		return (year%400 == 0) || ((year%100) != 0 && (year%4 == 0));
	}

	public static void main(String[] args) {

	}
}
