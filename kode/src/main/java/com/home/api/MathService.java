/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 
 */
public class MathService{
    
    public static void main(String args[]){
        int n = 5;
        //System.out.println("n="+isPrime(n));
        double x = Math.pow(n, n);
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
