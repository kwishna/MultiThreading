package Algos;

public class Algo2 {
    public static void printDuplicates(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { // Bubble
            for (int j = i + 1; j < arr.length; j++) {
                System.out.printf("Comparising Index: %d Vs %d", i, j);
                System.out.println();
                if(arr[i] == arr[j]) {
                    System.out.printf("The Duplicates Is: %d", arr[i]);
                    System.out.println();
                }
            }
            System.out.println("-------------------------------");
        }
    }

    public static int binarySearch(int[] arr, int search) {
            int low = 0;
            int high = arr.length - 1;

            while(low <= high){
                int mid = low + (high - low)/2;

                if(arr[mid] == search)
                    return mid;

                else if(arr[mid] < search)
                    low = mid + 1;

                else high = mid - 1;
            }
            return -1; // Unsuccessful result
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,2,4,4,2,3};
        System.out.println(binarySearch(arr, 4));
    }
}
