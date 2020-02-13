package InterviewPractice;


interface A {
	short counter = 0;

	void countUp();

}

public class Mainsss extends Factorial implements A {
int x;
float f;
Object obj = new Object();
	Mainsss m;
	static int i = 5;
	int j = 5;
	public static void main(String[] args) {
		Mainsss t = new Mainsss();
		t.countUp();
		try {
			System.out.println(t.j);
		}
		catch (Exception ignored){

		}

		System.out.println(i);
		System.out.println(t.counter);
		char ch = '\ucafe';

				System.out.println("Caught IO Exception");
				}

	static public void methodA()
	{
		

}

	public void countUp() {
		System.out.println(i+j);
	}

}
