package Algos;

public class Algo1 {
    public static int findMajCandidate(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        int count = 1, maj = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int x = arr[i];
            if (x == maj) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                maj = x;
                count = 1;
            }
        }
        return maj;
    }

    public static boolean verifyMajCandidate(int[] arr, int maj) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int n = arr.length, count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == maj) {
                count++;
            }
            if (count > n / 2) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 3, 2, 2, 2, 1, 2, 1, 1};
        int maj = findMajCandidate(arr);
        System.out.println(maj);
    }
}
