package InterviewPractice2;

// Important
public class RomanToInteger {
    public static int getValue(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> -1;
        };
    }
    public static int romanToInt(String str) {
        int sum = 0;

        for (int i = 0; i < str.length(); i++) {
            char prev = str.charAt(i);
            int a = getValue(prev); // Value Of 1st One

            if (i+1 < str.length()) {
                char next = str.charAt(i+1);
                int b = getValue(next); // Value Of 2nd One

                if (a >= b) { // If 1st Value Is Equal To
                    sum = sum + a;
                }
                else { // If 1st Value Is Less Than 2nd One. Example: 'XL' => X = 10 & L = 50
                    sum = sum + b - a;
                    i++;
                }
            }
            else {
                sum = sum + a;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMIV"));
    }
}
