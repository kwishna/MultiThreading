package MultiThreadings;

import java.util.Arrays;

class ThreadDemo extends Thread{
	
	@Override
	public void run() { // Job Of The Thread (Run By Child Thread)
		for (int i = 0; i < 5; i++) System.out.println("Running Child Thread Of Main : "+Thread.currentThread().getName());
	}
	
	public void start() { // Don't Override start() Method. If You Are Doing So. Make Sure To Call super.start(). Because Only Then Thread Will Start.
		super.start();
		for (int i = 0; i < 5; i++) System.out.println(":::: Thread Starting ::: "+Thread.currentThread().getName());
	}
}

public class MultiThreadOne{

	public static void main(String[] args) { // Main Thread
		System.out.println(Arrays.toString(args));
		ThreadDemo one = new ThreadDemo(); //Thread Instantiation
		one.start(); // Starting Thread Inside Main Thread (Child Thread)
		// Main Thread Starts Child Thread.
		// Main Method Is Different And Main Thread Is Different
		for (int i = 0; i < 5; i++) { // Run By Main Thread
			System.out.println("Running Main Thread : "+Thread.currentThread().getName());
		}
	}

}
