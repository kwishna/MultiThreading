package ThreadLocals;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UsingThreadLocal {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService exec = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<10; i++) {
			
//			exec.submit(() -> System.out.println(DriverHandlerJava8Onwards.local.get()+" "+Thread.currentThread().getName()));
//			exec.submit(() -> System.out.println(DriverHandlerBelowJava8.local.get()+" "+Thread.currentThread().getName()));
		
			Future<Integer> fut = exec.submit(new FutureCompleteAsync());
			System.out.println(fut.get());
		}

		DriverHandlerJava8Onwards.loc.set(""); // Just A selenium.Rough Line
		exec.shutdown();
	}
}

class DriverHandlerBelowJava8{ // First Program
	
	private DriverHandlerBelowJava8() {
		
	}
	
	static ThreadLocal<LocalTime> local = new ThreadLocal<LocalTime>() {
		
		@Override
		public LocalTime initialValue() {
		
			return LocalTime.now();
		}
	};
}

class DriverHandlerJava8Onwards{ // 2nd Program
	
	private DriverHandlerJava8Onwards() {
		
	}
	
	static ThreadLocal<LocalTime> local = ThreadLocal.withInitial(LocalTime::now); // Using Method Reference. JDK 8.
	static ThreadLocal<String> loc = new ThreadLocal<>();
}

class FutureCompleteAsync implements Callable<Integer>{ // 3rd Program

	@Override
	public Integer call() throws Exception {
		
		return new Random().nextInt(10);
		
	}
	
}