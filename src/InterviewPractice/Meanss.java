package InterviewPractice;

//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

public class Meanss {
	private static int maxNumberOfRacers(int x, int[][] arr) {
		int m = arr.length;
		int n = arr[0].length;
		int[] a = new int[m * n];
		int max = Integer.MIN_VALUE;
		int k = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int val = arr[i][j];
				a[k++] = val;

				if (val > max) {
					max = val;
				}
			}
		}

		int maxCount = Integer.MIN_VALUE;
		for (int i = 1; i < max; i++) {
			int count = 0;
			for (int j = 0; j < m * n; j = j + 2) {
				if (i >= a[j] && i < a[j + 1]) {
					count++;
				}

				if (count > maxCount) {
					maxCount = count;
				}
			}

		}

		return maxCount;
	}

	public static void main(String[] args) {
		System.out.println(maxNumberOfRacers(5, new int[][]{{1, 7}, {2, 4}, {6, 9}, {3, 8}, {5, 10}}));
		System.out.println(maxNumberOfRacers(4, new int[][]{{1, 3}, {2, 5}, {2, 4}, {3, 5}}));
	}
}
