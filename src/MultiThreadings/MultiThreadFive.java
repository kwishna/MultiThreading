package MultiThreadings;

class ThreadDemo3 extends Thread{

	public void run() { // To Be Executed By Thread-0	
		
		for(int i = 0; i < 5; i++) {
			System.out.println(i+" ::: "+Thread.currentThread().getName());		
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class MultiThreadFive {

	public static void main(String[] args) {

		ThreadDemo3 t1 = new ThreadDemo3();
		t1.start();//Executed By [Main] Thread
		
		try {
			t1.join(); //This Line Executed By [Main] Thread. Means Main Thread Will Wait Till Thread-0 Completes.
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) { // To Be Executed By Main Thread.
			System.out.println(i+" ::: "+Thread.currentThread().getName());
		}
	}
}
