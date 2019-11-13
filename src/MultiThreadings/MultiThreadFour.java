package MultiThreadings;

class ThreadDemo4 extends Thread {
	public void run() {
		
		for (int i = 0; i < 5; i++) {
			Thread.yield(); //Leaving Processor Every Time. Hence, It Won't Complete Execution Before [Main] Thread
							// Main Thread Also Has Priority 5. But, Thread-0 Is Giving Chance To Main Always.
			System.out.println(" Running Thread ::: "+Thread.currentThread().getName()+" :: With Priority :: "
					+Thread.currentThread().getPriority());
		} } }

public class MultiThreadFour {

	public static void main(String[] args) {	
		ThreadDemo4 t1 = new ThreadDemo4();
		t1.start();

		for (int i = 0; i < 5; i++) {
			System.out.println(" Running Thread ::: "+Thread.currentThread().getName()+" :: With Priority :: "
					+Thread.currentThread().getPriority());
		} } }
