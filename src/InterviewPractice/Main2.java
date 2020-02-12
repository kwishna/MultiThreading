package InterviewPractice;

import java.util.*;
public class Main2 {

	public static List<Integer> fun(int a[]){

		int n = a.length;
		List<List<Integer>> sets = new ArrayList<List<Integer>>();

		for(int i=0;i<(1<<n);i++){
			List<Integer> temp = new ArrayList<Integer>();
			for(int j=0;j<n;j++){
				if((i & (1<<j))>0){
					temp.add(a[j]);
				}
			}
			if(temp.size()!=0){
				sets.add(temp);
			}
		}
		System.out.println(sets);

		double mean =0,median=0,max=0;
		int resultant_index =0 ;
		for(List<Integer> p: sets){
			mean = (double)(p.stream().mapToInt(Integer::intValue).sum())/p.size();

			Integer t[] = p.toArray(new Integer[p.size()]);
			int l = t.length;
			if(l % 2 !=0){
				median = (double)t[l/2];
			}else
				median = (double)(t[l/2] +(t[(l-1)/2]))/2.0;

			double diff = mean - median;
			if((diff) > max){
				max = mean - median;
				resultant_index = sets.indexOf(p);
			}
			System.out.println("Mean - "+mean+", Median - "+median+", Difference - "+diff+", List - "+p);
		}
		return sets.get(resultant_index);
	}

	public static void main(String[] args) {

		int a[]= {1,2,3,4};
		System.out.println(fun(a));
	}
}
