/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * 
 */
public class MathService{
    
    public static void main(String args[]){
        //int n = 5;
        //System.out.println("n="+isPrime(n));
        //double x = Math.pow(n, n);
        callCheckForPair();
    }
    
    private static void callCheckForPair(){
        int[] arr = {1,3,4,6,8,9};
        int val = 8;
        boolean result = checkForPair(arr,val);
        //System.out.println("Result ="+result);
        result = checkForTriplet(arr,val);
        System.out.println("Result ="+result);
    }
    
    /**
     * Given an array, find if 2 numbers in the array sum up to a value
     * 
     * Iterate the array
     *  check if value-arr[i] is present in the set.
     *  if not present, add arr[i] to the set
     *  otherwise return true;
     * 
     */
    
    public static boolean checkForPair(int[] arr, int value){
        boolean result = false;
        Set<Integer> hs = new HashSet<>();
        for(int ar : arr){
            if(hs.contains(value-ar)){
                return true;
            }
            else{
                hs.add(ar);
            }
        }
        
        return result;
    }
    
    
    /***
     * - Sort the array
     * - for each elem, find 2 other elems, one pointing to the next 
     * and the other pointing to the last.
     * 
     * 
     */
    public static boolean checkForTriplet(int[] arr, int value){
        boolean result = false;
        Arrays.sort(arr);
        for (int i=0; i < arr.length-2; i++){
            int first = arr[i];
            int j = i+1;
            int k = arr.length-1;
            while(j<k){
                int sum = arr[i]+arr[j]+arr[k];
                if(sum < value){
                    j++;
                }
                else if (sum > value){
                    k--;
                }
                else{
                    return true;
                }
            }
        }
        return result;
        
    }
    
    public static int pow(int base, int exp){
        int result=1;
        
        while(exp!=0){
            if((exp & 1) == 1){
                result *=base;
            }
            exp >>=1;
            base *= base;
        }
        
        return result;
    }
    
    /*
     * Generate primes numbers from 0 to n
     * 
     * Initialize a boolean array of size n
     * Set all values as TRUE;
     * from i = 2 to sqrt ( n) : do 
     * if(!i)
     *      set arr[i], arr[2i], arr[3i] to false.
     * 
     *   
     * 
     */
    public static List<Integer> primeNumbers(int n){
        ArrayList<Integer> resultList= new ArrayList<>();
        boolean [] numsFlags = new boolean[n+1];
        Arrays.fill(numsFlags, true);
        numsFlags[0]=false;
        numsFlags[1]=false;
        for(int i=2 ; i*i<= n; i++){
            if(numsFlags[i]){
               for (int j = 2*i; j<=n;j+=i){
                   numsFlags[j]=false;
               } 
            }
        }
        
        for(int i =0 ; i< numsFlags.length; i++){
            if(numsFlags[i]){
                resultList.add(i);
            }
        }
        return resultList;
    }
    
    public static boolean isPrime(int n ){
        if(n<2){
            return false;
        }
        
        for(int i =2; i*i<=n; i++){
            if(n%i == 0){
                return false;
            }
        }
        
        return true;
    }
}
