package InterviewPractice;

public class Try {

	public static void main(String[] args) {
		String s = "Hello World";
		StringBuilder builder = new StringBuilder();
		s.chars().distinct().forEach(c -> builder.append((char) c));

//		Arrays.asList("123947934642".split("")).stream().distinct().toArray(new String[]);
	}
}
