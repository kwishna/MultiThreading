package MultiThreadings;

class RunnableDemo implements Runnable{

	public void run() {
		
		for (int i = 0; i < 5; i++) {
			System.out.println(i+" ::: "+Thread.currentThread().getName());
			
		}
	}
	
}

public class MultiThreadTwo {

	public static void main(String[] args) {
		
		RunnableDemo r1 = new RunnableDemo();
	//	r1.start(); // Doesn't Create Any Thread. start() Is Present Only In Thread Class
	//	r1.run();// Doesn't Create Any Thread. Just Runs The run() Method.
		
//		Thread t1 = new Thread();
//		t1.start(); // Creates A Thread. Runs It's Own run() Method. Which Has No Code Inside.
//		t1.run();// Doesn't Creates A Thread. Runs It's Own run() Method. Which Has No Code Inside.
		
		Thread t2 = new Thread(r1);
		t2.start(); // Creates The Thread An Invokes Run Method Of RunnableDemo Class
//		t2.run(); // Doesn't Create Any Thread. Invokes Run Method Of RunnableDemo Class.
		
		for (int i = 0; i < 5; i++) {
			System.out.println(i+" ::: "+Thread.currentThread().getName());
			
		}
	}

}
