import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rough {

	public static boolean isValidSequence(String seq) {
		List<String> stringArr = seq.chars().boxed().map(Character::toString).collect(Collectors.toList());
		int n = stringArr.size();
		for (int i = 1; i < n; i++) {
			String ch = stringArr.get(i);
			if (ch.equals(")") || ch.equals("}") || ch.equals("]")) {
				if (stringArr.get(i - 1).equals(getOpposite(stringArr.get(i)))) {
					stringArr.remove(i);
					stringArr.remove(i - 1);
					i -= 2;
				}
			}
			n = stringArr.size();
		}
		System.out.println(stringArr);
		int i = 10;
		Arrays.stream(new int[]{1, 2, 3}).filter(c -> c == i).boxed().collect(Collectors.toList());
		return stringArr.size() == 0;
	}

	public static String getOpposite(String s) {
		switch (s) {
			case "(":
				return ")";
			case "{":
				return "}";
			case "[":
				return "]";
			case ")":
				return "(";
			case "}":
				return "{";
			case "]":
				return "[";
			default:
				return null;
		}
	}

	public static void main(String[] args) {
//		System.out.println(isValidSequence("[{(())}{(){}}]"));
//		System.out.println(isValidSequence("[{(())}{(){}}]"));
//		System.out.println(isValidSequence("{([([)]])}"));
		System.out.println(isValidSequence("({})"));
	}
}
