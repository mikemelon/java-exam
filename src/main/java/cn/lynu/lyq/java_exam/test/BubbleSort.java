package cn.lynu.lyq.java_exam.test;

import java.util.Arrays;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] array ={ 4, 8, 3, 2, 9, 10, 6, 2, 8, 7, 1};
		int[] index= new int[array.length];
		for(int i=0; i<array.length; i++){
			index[i] = i;
		}
		sortWithIndex(array,index);
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(index));
	}
	
	public static void sortWithIndex(int[] array, int[] index){
//		index = new int[array.length];//原始下标数组
//		for(int i=0; i<array.length; i++){
//			index[i] = i;
//		}
		for(int i=0; i<array.length; i++){//冒泡轮次i
			for(int j=0; j<array.length-i-1; j++){//参与比较的数组下标j
				if(array[j]<array[j+1]){
					int tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
					
					int tmp2 = index[j];
					index[j] = index[j+1];
					index[j+1] = tmp2;
				}
			}
		}
	}
	
}
