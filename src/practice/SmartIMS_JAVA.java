package practice;

/*
 * Write a program that prints the numbers from 1 to 100. But for multiples of 3 print “Three” instead of the number
 * and for the multiples of five print “Five”. For numbers which are multiple of both 3 and 5, print “Three and Five”.
 */
public class SmartIMS_JAVA {

	public static boolean itIsDivisibleBy3(int i) {
		return i % 3 == 0;
	}

	public static boolean itIsDivisibleBy5(int i) {
		return i % 5 == 0;
	}

	public static boolean itIsDivisibleBy3And5(int i) {
		return i % 5 == 0;
	}

	public static void main(String... args) {
		java.util.stream.IntStream.rangeClosed(1, 100).forEach(x -> {
			if (itIsDivisibleBy3And5(x)) System.out.println("Three and Five");
			else if (itIsDivisibleBy3(x)) System.out.println("Three");
			else if (itIsDivisibleBy5(x)) System.out.println("Five");
			else System.out.println(x);
		});
	}
}