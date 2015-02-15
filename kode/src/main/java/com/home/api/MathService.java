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
        callFlipBits();
    }
    
    private static void testReversetBits(){
        int input = 5;
        
        int output = reverseBits(input);
        System.out.println(" input = "+Integer.toBinaryString(input));
        System.out.println(" output = "+Integer.toBinaryString(output));
        //System.out.println(" output 2= "+Integer.toBinaryString(Integer.reverse(input)));
        long input1 = 15L;
        long output1 = reverse64Bits(input1);
        System.out.println(" long input  = "+Long.toBinaryString(input1));
        System.out.println(" long output = "+Long.toBinaryString(output1));
    }
    private static void callUtopian(){
        System.out.println("utopian at 4 ="+utopianTree(4));
        System.out.println("utopian at 3 ="+utopianTree(3));
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
     * a tree of height 1 is given
     * the number of growth cycle is given as times.
     * First growth cycle doubles the height.
     * The next growth cycle increments the height by 1;
     * 
     */
    private static int utopianTree(int times){
        int result = 1;
        for(int i = 1; i<=times; i++){
            if(i % 2 == 1){
                result *=2;
            }
            else{
                result++;
            }
        }
        return result;
    }
    
    private static void callFlipBits(){
        System.out.println("2147483647 =>"+flipBits(2147483647));
        System.out.println("1 =>"+flipBits(1));
        System.out.println("0 =>"+flipBits(0));
        System.out.println("4294967295 =>"+flipBits(4294967295L));
    }
    
    /**
     * Flips all the bits of the incoming data
     *
     */
    private static long flipBits(long data){
        if(data > 10000000000L){
            return 0;
        }
        // convert to long,
        // flip the bits,
        // mask ( do an AND) with 00000000FFFFFFF
        //if(data)
        long val = data;
        long flippedLong = val ^ 0xFFFFFFFFFFFFFFFFL;
        long clippedLong = flippedLong & 0x00000000FFFFFFFFL; 
        //int result = data ^ 0xFFFFFFFF;
        System.out.println("flipped before = "+Long.toBinaryString(val));
        System.out.println("flipped flipped = "+Long.toBinaryString(flippedLong));
        System.out.println("flipped after = "+Long.toBinaryString(clippedLong));
        return clippedLong;
    }
    /**
     * in the given array , all numbers appear twice except for one.
     **/
    private static int lonelyinteger(int[] a) {

        int result = 0;
        for(int num : a){
            result = result^num;
        }
        return result;

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
    
    public static int reverseBits(int num){
        int a1 = 0x55555555, a2=0xAAAAAAAA; // ...0101 and ... 1010
        int b1 = 0x33333333, b2=0xCCCCCCCC; // ...0011 and ... 1100
        int c1 = 0x0F0F0F0F, c2=0xF0F0F0F0;// ... 00001111 and .. 11110000
        int d1 = 0x00FF00FF, d2=0xFF00FF00; // .... 0000000011111111 and .. 1111111100000000
        int e1 = 0x0000FFFF, e2=0xFFFF0000; 
                
        int result = num;
        
        result = ((result & a1) << 1) | ((result & a2) >> 1);
        result = ((result & b1) << 2) | ((result & b2) >> 2);
        result = ((result & c1) << 4) | ((result & c2) >> 4);
        result = ((result & d1) << 8) | ((result & d2) >> 8);
        result = ((result & e1) << 16) | ((result & e2) >> 16);
        //System.out.println("55 ="+0x55);
        return result;
    }
    
    public static long reverse64Bits(long num){
        long a1 = 0x5555555555555555L, a2=0xAAAAAAAAAAAAAAAAL; // ...0101 and ... 1010
        long b1 = 0x3333333333333333L, b2=0xCCCCCCCCCCCCCCCCL; // ...0011 and ... 1100
        long c1 = 0x0F0F0F0F0F0F0F0FL, c2=0xF0F0F0F0F0F0F0F0L;// ... 00001111 and .. 11110000
        long d1 = 0x00FF00FF00FF00FFL, d2=0xFF00FF00FF00FF00L; // .... 0000000011111111 and .. 1111111100000000
        long e1 = 0x0000FFFF0000FFFFL, e2=0xFFFF0000FFFF0000L; 
        long f1 = 0x00000000FFFFFFFFL, f2=0xFFFFFFFF00000000L; 
                
        long result = num;
        
        result = ((result & a1) << 1) | ((result & a2) >> 1);
        result = ((result & b1) << 2) | ((result & b2) >> 2);
        result = ((result & c1) << 4) | ((result & c2) >> 4);
        result = ((result & d1) << 8) | ((result & d2) >> 8);
        result = ((result & e1) << 16) | ((result & e2) >> 16);
        result = ((result & f1) << 32) | ((result & f2) >> 32);
        //System.out.println("55 ="+0x55);
        return result;
    }
    
    
}
