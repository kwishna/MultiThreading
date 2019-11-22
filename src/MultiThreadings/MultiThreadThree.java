package MultiThreadings;

class ThreadPriority extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(" Running Thread ::: "+Thread.currentThread().getName()+" :: With Priority :: "
					+Thread.currentThread().getPriority());
		} } }

public class MultiThreadThree {

	public static void main(String[] args) {	
		ThreadPriority t1 = new ThreadPriority();
//		t1.setPriority(10);
		t1.start();
		
		for (int i = 0; i < 5; i++) {
			System.out.println(" Running Thread ::: "+Thread.currentThread().getName()+" :: With Priority :: "
					+Thread.currentThread().getPriority());
		} } }
