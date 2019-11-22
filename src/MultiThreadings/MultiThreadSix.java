package MultiThreadings;

class ThreadDemo5 extends Thread {

	static Thread mt;

	public void run() { // To Be Executed By Thread-0
		try {
			mt.join(); // This Line Executed By Thread-0 Thread. Means Thread-0 Thread Will Wait Till
						// Main Completes.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(i + " ::: " + Thread.currentThread().getName());
		}
	}
}

public class MultiThreadSix {

	public static void main(String[] args) {

		ThreadDemo5.mt = Thread.currentThread(); // Assigning Main Thread To mt.
		ThreadDemo5 t1 = new ThreadDemo5();
		t1.start();// Executed By [Main] Thread

		for (int i = 0; i < 5; i++) { // To Be Executed By Main Thread.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println(i + " ::: " + Thread.currentThread().getName());
		}
	}
}

class Something implements Runnable {

	int i = 0; // This Is A Instance Variable. It Is Same For An Object In Any Thread.

	@Override
	public void run() {
		int j = 5; // It's Memory Is Not Shared Among All The Threads.
		System.out.println(Thread.currentThread().getName() + " | " + " i -> " + i + " | " + " j -> " + j);
		i++;
		j++;

	}
}
