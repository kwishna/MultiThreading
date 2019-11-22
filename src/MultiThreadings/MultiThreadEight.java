package MultiThreadings;

class RunnableDemo1 implements Runnable{
	
//	volatile int i=0;
	int i = 0;
	
	public void run() {	
		for (i = 0; i < 5; i++) {
			System.out.println(i+" ::: "+Thread.currentThread().getName());
		}
		m1();
	}
	
	synchronized void m1() {
		for (int j = 0; j < 5; j++) {
			System.out.println(j+" ::: method ::: "+Thread.currentThread().getName());
		}	
	}
}

public class MultiThreadEight {
	
	public static void main(String[] args) {
		
		RunnableDemo1 r1 = new RunnableDemo1();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		
		t1.start();
		t2.start();
	}
}
