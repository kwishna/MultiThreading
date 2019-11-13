package MultiThreadings;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadRough {
	
	public static void main(String[] args) {
		
		ExecutorService s = Executors.newFixedThreadPool(1);
		Some so = new Some();
		
		for(int i=0; i<20; i++) {
			s.execute(so);
		}
		s.shutdown();
	}

}

class Some implements Runnable{
	
	int i = 0; // This Is A Instance Variable. It Is Same For An Object In Any Thread.
	
	@Override
	public void run() {
		int j = 5; // It's Memory Is Not Shared Among All The Threads.
		System.out.println(Thread.currentThread().getName()+" | "+" i -> "+i+" | "+" j -> "+j);
		i++;
		j++;
		
	}
}
