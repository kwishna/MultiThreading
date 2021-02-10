import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		String name = "Krishna";
		System.out.println(name.chars().boxed().map(Character::toString).collect(Collectors.toList()));
	}
}
