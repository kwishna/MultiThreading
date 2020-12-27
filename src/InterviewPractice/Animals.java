package InterviewPractice;


class Pancake {
}

class BlueberryPancake extends Pancake{
}

public class Animals extends BlueberryPancake {
	public static void main(String[] args) {
		Pancake a1 = new Animals();
		Pancake a2 = a1;
		BlueberryPancake a3 = (BlueberryPancake) a1;
		BlueberryPancake a4  = (Animals) a1;
//		Animals a6 = (BlueberryPancake) a1; // Error
		Animals a5 = (Animals) a1;

	}
}
