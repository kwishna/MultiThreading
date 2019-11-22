package MultiThreadings;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadNine {
public static void main(String[] args) throws InterruptedException {
		
		ClassWithMethods c = new ClassWithMethods();
		
		Thread t1 = new Thread(new Runnable() { // new Runnable(){  } Means An Annonymous Implementing Runnable Interface

			@Override
			public void run() {
				
				for(int i=0; i<1000; i++) {
								
					c.doSomething();
				}
			}
			
		}); // Thread-0
		
		
		Thread t2 = new Thread(new Runnable() { // new Runnable(){  } Means An Annonymous Implementing Runnable Interface

			@Override
			public void run() {
				
				for(int i=0; i<1000; i++) {
				
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

class ClassWithMethods {
	
	private AtomicInteger i = new AtomicInteger(0);
	
	public void doSomething() {
		
		i.incrementAndGet();
		
	}
	
	public AtomicInteger getValue() {
		return i;
	}
}
