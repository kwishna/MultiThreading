package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Logical :-
 * 		XY	Matches X and Y (X followed by Y).
 * 		X|Y	Matches X or Y.
 *
 *		Greedy		Reluctant	Possessive	Matches
 *		X?			X??			X?+			Matches X once, or not at all (0 or 1 time).
 *		X*			X*?			X*+			Matches X zero or more times.
 *		X+			X+?			X++			Matches X one or more times.
 *		X{n}		X{n}?		X{n}+		Matches X exactly n times.
 *		X{n,}		X{n,}?		X{n,}+		Matches X at least n times.
 *		X{n, m)		X{n, m)?	X{n, m)+	Matches X at least n time, but at most m times
 *
 *
 *		Construct	Matches
 * 		.			Matches any single character. May or may not match line terminators, depending on what flags were used to compile the Pattern.
 * 		\d			Matches any digit [0-9]
 * 		\D			Matches any non-digit character [^0-9]
 * 		\s			Matches any white space character (space, tab, line break, carriage return)
 * 		\S			Matches any non-white space character.
 * 		\w			Matches any word character.
 * 		\W			Matches any non-word character.
 * 		\S+			Several non-whitespace characters
 *
 *
 */
public class Regex {

	private static final String regex = "([a-zA-Z_]+){2}([0-9_]+)";
	private static final String text = "NORTHFACE_FR_Desktop_EN_20191010_101010";

	public static void main(String[] args) {

		Matcher matcher = Pattern.compile(regex).matcher(text);
		while (matcher.find()){
			System.out.println(matcher.start());
			System.out.println(matcher.end());
			System.out.println(matcher.group());
		}
	}
}
