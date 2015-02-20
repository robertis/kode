/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.Arrays;

/**
 *
 * @author rtongbram
 */
public class ArrayService {

    public static void main(String args[]) {

        int[] arr = {2, 4, 6, 8, 19};
        //int[] arr = { 2,5, 6, 8};
        int[] arr2 = {1, 3, 4, 7,36};
        int[] arr3 = {0, 5, 9};

        //System.out.println("getSmallestMissingPositiveNumber of arr=" + getSmallestMissingPositiveNumber(arr));
        System.out.println("merge of arrs=" + Arrays.toString(mergeSortedArrays(arr, arr2)));
        System.out.println("merge of arrs=" + Arrays.toString(merge3Arrays(arr, arr2, arr3)));
    }
    
    public static int[] mergeArrays(int[] a, int[]b, int[] c){
        int[] result = new int[a.length+b.length+c.length];
        
        return result;
    }
    
    
    public static int[] merge3Arrays(int[] a, int[]b, int[] c){
        int[] result = new int[a.length+b.length+c.length];
        int max = Integer.MAX_VALUE;
        int i=0,j=0,k=0, n=0;
        int av=max, bv=max, cv=max;
        while(n<result.length){
            if(i<a.length){
                av=a[i];
            }
            if(j<b.length){
                bv=b[j];
            }
            if(k<c.length){
                cv=c[k];
            }
            
            if(av<bv){
                if(av<cv){
                    result[n++]=av;
                    i++;
                    av=max;
                }
                else{
                    result[n++]=cv;
                    k++;
                    cv=max;
                }
            }
            else{
                if(bv<cv){
                    result[n++]=bv;
                    j++;
                    bv=max;
                }
                else{
                    result[n++]=cv;
                    k++;
                    cv=max;
                }
            }
        }
        
        return result;
        
    }
 
    public static int[] mergeSortedArrays(int[] a, int[] b) {

        int i = a.length - 1, j = b.length - 1, k = a.length + b.length - 1;
        int[] result = new int[k + 1];
        while (i >= 0 && j >= 0) {
            //System.out.println("i="+i+".. j = "+j+"... k = "+k);
            //System.out.println("a["+i+"]="+a[i]+".. b["+j+"] = "+b[j]);
            if (a[i] >= b[j]) {
                result[k] = a[i];
                i--;
            } else {
                result[k] = b[j];
                j--;
            }
            k--;
        }
        if (i >= 0) {
            while (i >= 0) {
                result[k] = a[i];
                i--;
                k--;
            }
        } else if (j >= 0) {
            while (j >= 0) {
                result[k] = b[j];
                j--;
                k--;
            }
        }
        return result;
    }
    
    public static int getSmallestMissingPositiveNumber(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && arr[i] <= arr.length) {
                while (i + 1 != arr[i]) {
                    if (arr[i] < 0 || arr[i] > arr.length || arr[i] == arr[arr[i] - 1]) {
                        break;
                    }
                    if (arr[i] > 0 && arr[i] <= arr.length) {
                        int tmp = arr[arr[i] - 1];
                        arr[arr[i] - 1] = arr[i];
                        arr[i] = tmp;
                    }
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (i != arr[i] - 1) {
                return i + 1;
            }
        }
        return -1;
    }
}
