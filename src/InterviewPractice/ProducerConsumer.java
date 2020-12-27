package InterviewPractice;
import java.util.LinkedList;

/**
 * Java program to implement solution of producer-consumer problem.
 * More : https://www.journaldev.com/1034/java-blockingqueue-example
 */
public class ProducerConsumer {
	public static void main(String[] args)
			throws InterruptedException
	{
		// Object of a class that has both produce() and consume() methods
		final PC pc = new PC();

		// Create producer thread
		Thread t1 = new Thread(() -> {
			try {
				pc.produce();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// Create consumer thread
		Thread t2 = new Thread(() -> {
			try {
				pc.consume();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// Start both threads
		t1.start();
		t2.start();

		// t1 finishes before t2
		t1.join(); // Main Thread Goes Into Waiting State Until t1 Finishes.
		t2.join(); // Main Thread Goes Into Waiting State Until t2 Finishes.

		/*
		 * join() :- Calling Thread Goes Into Waiting State Until Called Thread Completes Its Task.
		 * yield() :- Give Chance To Another Waiting Thread By Releasing Locks Current Held With It.
		 * sleep() :- Pause The Execution For Provided Duration.
		 * wait() :- Wait Until Some Other Thread Notifies.
		 * notify() :- Notify Arbitrary Any One Of The Waiting Thread Who Is Waiting For Lock On Locked(synchronized) Object To Wake Up.
		 * notifyAll() :- Notify All Waiting Thread Who Are Waiting For Lock On Locked(synchronized) Object To Wake Up.
		 */

		t1.notify();
	}

	// This class has a list, producer (adds items to list and consumber (removes items).
	public static class PC {

		// Create a list shared by producer and consumer
		// Size of list is 2.
		LinkedList<Integer> list = new LinkedList<>();
		int capacity = 2;

		// Function called by producer thread
		public void produce() throws InterruptedException
		{
			int value = 0;
			while (true) {
				synchronized (this)
				{
					// producer thread waits while list is full
					while (list.size() == capacity)
						wait();

					System.out.println("Producer produced-"
							+ value);

					// to insert the jobs in the list
					list.add(value++);

					// notifies the consumer thread that now it can start consuming
					notify();

					// makes the working of program easier to understand
					Thread.sleep(1000);
				}
			}
		}

		// Function called by consumer thread
		public void consume() throws InterruptedException
		{
			while (true) {
				synchronized (this)
				{
					// consumer thread waits while list is empty
					while (list.size() == 0)
						wait();

					// to retrive the ifrst job in the list
					int val = list.removeFirst();

					System.out.println("Consumer consumed-"
							+ val);

					// Wake up producer thread
					notify();

					// and sleep
					Thread.sleep(1000);
				}
			}
		}
	}
}