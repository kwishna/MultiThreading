package InterviewPractice2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SumOf2Numbers {

    public static void main(String[] args) {
        int sum = 15;
        int[] nums = new int[]{3, 1, 10, 4, 1, 6, 7, 2, 9, 1, 5, 3, 4};
        find2numbers(sum, nums);
        find2numbersIndex(sum, nums);
    }

    private static void find2numbers(int sum, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int a : nums) {
            int rem = sum - a;
            if (!set.contains(rem)) {
                set.add(a);
            } else {
                System.out.printf("| The 2 No Is %d and %d |", a, rem);
                System.out.println();
            }
        }
        System.out.println("--------------------");
    }

    private static void find2numbersIndex(int sum, int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int rem = sum - nums[i];
            if (!m.containsKey(rem)) {
                m.put(nums[i], i);
            }
            else {
                System.out.printf("| Indices Are %d and %d |", m.get(rem), i);
                System.out.println();
            }
        }
        System.out.println("--------------------");
    }
}
