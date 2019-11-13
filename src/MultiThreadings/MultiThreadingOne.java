package MultiThreadings;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadingOne {

	public static void main(String[] args) throws InterruptedException {

		ClassWithSomeMethods c = new ClassWithSomeMethods();

		Thread t1 = new Thread(new Runnable() { // new Runnable(){ } Means An Annonymous Implementing Runnable Interface

			@Override
			public void run() {

				for (int i = 0; i < 1000; i++) {

					c.doSomething();
				}
			}

		}); // Thread-0

		Thread t2 = new Thread(new Runnable() { // new Runnable(){ } Means An Annonymous Implementing Runnable Interface

			@Override
			public void run() {

				for (int i = 0; i < 1000; i++) {

					c.doSomething();
				}
			}

		}); // Thread-1

		// Start Running Threads
		t1.start();
		t2.start();

		// Now Main Thread Waits For Both Threads To Stop Its Task
		t1.join();
		t2.join();

		System.out.println(c.getValue());
	}
}

class ClassWithSomeMethods {

	int i = 0;

//	public synchronized void doSomething() { // Any One Thread Can Access At One Time.
	public void doSomething() {

		/*
		 * At This Point, Value Of i Is Getting Increased And Then Assigned To The i
		 * Back. Hence, There Are Multiple Actions Happening Here. When 2 Thread Comes
		 * Together And Asks For The Current Value. i Shows Its Value As 1 To Both The
		 * Threads. Then Both Thread Tries To Increase The Value. So, After Both Thread
		 * Has Completed Its Work. It's 2 Only.
		 * 
		 * Solution : Make The Method synchronzied.
		 */
		
		i++;

	}

	public int getValue() {

		return i;
	}
}
