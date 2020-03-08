package com.ql.algorithm;

import java.util.Arrays;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2020-02-12 10:10
 * @Version 1.0
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    static void quickSort(int R[], int lo, int hi){
        int i = lo, j = hi;
        int temp;
        if(i < j){
            temp = R[i];
            while (i != j)
            {
                while(j > i && R[j] > temp)-- j;
                R[i] = R[j];
                while(i < j && R[i] < temp)++ i;
                R[j] = R[i];
            }
            R[i] = temp;
            quickSort(R, lo, i - 1);
            quickSort(R, i + 1, hi);
        }
    }
}
