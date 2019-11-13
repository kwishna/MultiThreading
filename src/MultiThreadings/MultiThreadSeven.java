package MultiThreadings;

class ThreadDemo6 extends Thread{

	public void run() { // To Be Executed By Thread-0	
		
		for(int i = 0; i < 5; i++) {
			System.out.println(i+" ::: "+Thread.currentThread().getName());		
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()+" ::: "+e.getMessage());
			}
		}
	}
}

public class MultiThreadSeven {

	public static void main(String[] args) {

		ThreadDemo6 t1 = new ThreadDemo6();
		t1.start();//Executed By [Main] Thread

		t1.interrupt(); // This Line Executed By [Main] Thread. The Thread-0 Is Interrupted By [Main] Thread.

		for (int i = 0; i < 5; i++) { // To Be Executed By Main Thread.
			System.out.println(i+" ::: "+Thread.currentThread().getName());
		}
	}
}
