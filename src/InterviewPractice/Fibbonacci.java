package InterviewPractice;

/**
 * Fibonacci Series Using While & For Loop!
 */
public class Fibbonacci {

	public static int printNthFibo(int i){

		int fibo = 1;

		if(i == 1 || i == 2){
			fibo = 1;
		}

		else if(i > 2){
			fibo = printNthFibo(i-1) + printNthFibo(i-2); // Fibo Of 3rd Equals To Fibo Of 2 Plus Fibo Of 1.
		}

		return fibo;
	}

	public static int printXthFibo(int i){

		int fibonacci = 1;

		if(i == 1 || i == 2){
			return 1;
		}

		int first = 1;
		int second = 1;

		for(int j=2; j<i; j++) {
			fibonacci = second + first;
			first = second;
			second = fibonacci;
		}

		return fibonacci;
	}

	public static void main(String[] args) {

		System.out.println(printNthFibo(10));

		for (int i=1; i<=10; i++) {
			System.out.print(printXthFibo(i));
			System.out.print(", ");
		}
	}
}
