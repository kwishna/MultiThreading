package MultiThreadings;

public class DeadLockTest {

	public static void main(String[] args) {
		DeadLockTest test = new DeadLockTest();

		final A a = test.new A();
		final B b = test.new B();

		// Thread-1
		Runnable block1 = new Runnable() {
			public void run() {
				synchronized (a) {
					try {
						// Adding delay so that both threads can start trying to
						// lock resources
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Thread-1 have A but need B also
					synchronized (b) {
						System.out.println("In block 1");
					}
				}
			}
		};

		// Thread-2
		Runnable block2 = new Runnable() {
			public void run() {
				synchronized (b) {
					// Thread-2 have B but need A also
					synchronized (a) {
						System.out.println("In block 2");
					}
				}
			}
		};

		new Thread(block1).start();
		new Thread(block2).start();


		/* **************************** RESOLVING THREAD *************************** */
		DeadLockTest test1 = new DeadLockTest();
		final C c = test1.new C();
		final D d = test1.new D();

		// Thread-3
		Runnable block3 = new Runnable() {
			public void run() {
				synchronized (d) {
					try {
						// Adding delay so that both threads can start trying to
						// lock resources
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Thread-1 have A but need B also
					synchronized (c) {
						System.out.println("In block 1");
					}
				}
			}
		};

		// Thread-4
		Runnable block4 = new Runnable() {
			public void run() {
				synchronized (d) {
					// Thread-2 have B but need A also
					synchronized (c) {
						System.out.println("In block 2");
					}
				}
			}
		};

		new Thread(block3).start();
		new Thread(block4).start();
	}

	/**
	 *
	 * Thread Deadlock
	 *
	 */

	// Resource A
	private class A {
		private int i = 10;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
	}

	// Resource B
	private class B {
		private int i = 20;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
	}

	/**
	 *
	 * Resolving Deadlock
	 *
	 */

	// Thread-1

	// Resource C
	private class C {
		private int i = 10;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
	}

	// Resource D
	private class D {
		private int i = 20;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
	}

}
