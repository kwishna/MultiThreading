package InterviewPractice;

public interface HelloInterface {

	public void name();

	public default void home() {

	}

	public static void age() {
		System.out.println("dffsdfds");
	}
}
