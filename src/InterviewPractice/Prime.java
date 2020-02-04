package InterviewPractice;

import java.util.stream.IntStream;

/**
 * Prime Number.
 */
public class Prime {

	public static boolean isPrime(int i){

		boolean isPrime = false;

		if(i==0 || i==1){
			isPrime = false;
		}

		else if (i==2 || i==3){
			isPrime = true;
		}

		else{
			for (int j=2; j<=Math.sqrt(i); j++){
				if(i%j == 0){
					isPrime = false;
					break;
				}
				else isPrime = true;
			}
		}
		return isPrime;
	}

	private static void printFirstNPrime(int value){

		int count = 0;
		for (int i = 2; i < Integer.MAX_VALUE; i++) {
			if(isPrime(i) && count<value){
				count++;
				System.out.print(i);
				System.out.print(", ");
			}
			else if (count == value) break;
		}
	}

	public static void main(String[] args) {

		IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15).filter(Prime::isPrime).forEach( a -> System.out.print(a + ", "));
		System.out.println();
		printFirstNPrime(10);
	}
}
