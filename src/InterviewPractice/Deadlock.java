package InterviewPractice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DEADLOCK EXAMPLE :-
 */
public class Deadlock {

	final String name = "Krishna Kumar Singh";
	final String home = "Bengaluru, India";

	public void method1() {
		synchronized (name) {
			System.out.println("Aquired lock on "+name+" object - "+Thread.currentThread());

			synchronized (home) {
				System.out.println("Aquired lock on "+home+" object - "+Thread.currentThread());
			}
		}
	}

	/*
	 * This method also requests same two lock but in exactly
	 * Opposite order i.e. first name and then home.
	 * This creates potential deadlock, if one thread holds name lock
	 * and other holds home lock and they wait for each other, forever.
	 */
	public void method2() {
		synchronized (home) {
			System.out.println("Aquired lock on "+home+" object - "+Thread.currentThread());

			synchronized (name) {
				System.out.println("Aquired lock on "+name+" object - "+Thread.currentThread());
			}
		}
	}

	final static Deadlock f = new Deadlock();

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(f::method1);
		service.submit(f::method2);

	}
}
