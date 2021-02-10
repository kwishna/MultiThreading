package practice;

import java.util.Map;
import java.util.TreeMap;

public class Pract {

	public static void main(String[] args) {
		Map<String, Integer> map = new TreeMap<>();
		String b1 = "1";
		String b2 = "2";
		String b3 = "3";

		map.put(b1, 1);
		map.put(b2, 1);
		map.put(b3, 1);

		System.out.println(map);
//		System.out.println(
//				Arrays.stream("as12&%$#$".split("")).filter(s -> s.matches("[^\\w\\d]"))
//						.collect(Collectors.joining())
//		);
	}
}
