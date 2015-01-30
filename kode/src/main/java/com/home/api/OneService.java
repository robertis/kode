/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 *
 * @author rtongbram
 */
public class OneService {
    
    //Find the smallest number which cannot be constructed from the array.
    // we check if the input val > sum+1 because, we know that sum is constructible
    // and if the next bigger number val ( array is sorted), is bigger than sum+1,
    // sum+1 is never constructible.
    public static int smallestNonConstructibleChange(int[] input){
        // sort the array
        Arrays.sort(input);
        int sum=0;
        for (int val : input){
            if(val > sum+1){
                break;
            }
            sum+=val;
        }
        return sum+1;
    }
    
    public static int findCelebrity(boolean[][] values){
        int result=-1;
        int i=0, j=1;
        if(values.length > 0){
            int numCols = values[0].length;
            
            while(j < numCols){
                // i doesnt know j : i can be a celebrity , but j cannot be a celeb
                if(values[i][j] == false){
                    j++;
                }
                // i knows j : i cannot be a celeb, j can be a celeb, 
                //but all elems between i and j are disqualified earlier. 
                //When we reached j, all elems between 1 to j have been tested
                else{
                  i=j;
                  j++;
                }
            }
            
        }
        result = i;
        return result;
    }
    
    public static int findGcdRec(int a, int b){
        
        if(a%b == 0){
            return b;
        }
        else{
            return findGcdRec(a,b%a);
        }
        
    }
    
    public static int evaluateRPN(String[] tokens){
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(String token : tokens){
            switch(token){
                case "*":
                    if (!stack.isEmpty() && stack.size() > 1) {
                        int num2 = stack.pop();
                        int num1 = stack.pop();
                        stack.push(num1 * num2);
                    }
                case "+":
                    if (!stack.isEmpty() && stack.size() > 1) {
                        int num2 = stack.pop();
                        int num1 = stack.pop();
                        stack.push(num1 + num2);
                    }
                case "-":
                    if (!stack.isEmpty() && stack.size() > 1) {
                        int num2 = stack.pop();
                        int num1 = stack.pop();
                        stack.push(num1 - num2);
                    }
                case "/":
                    if (!stack.isEmpty() && stack.size() > 1) {
                        int num2 = stack.pop();
                        int num1 = stack.pop();
                        stack.push(num1 / num2);
                    }
                default :
                    try{
                        stack.push(Integer.valueOf(token));
                    }
                    catch(Exception ex){
                        
                    }
            }
        }
        if(!stack.isEmpty()){
            result = stack.pop();
        }
        
        return result;
    }
    
    // convert base of input from base1 to base2
    // in base10 , "234" = 234
    // 10 => base 2 => 1010
    public static String convertBase(String input, int base1, int base2){
        String result = "";
        boolean isNeg = false;
        
        if(input!=null && !input.isEmpty()){
            if(input.startsWith("-")){
                isNeg=true;
                input=input.substring(1);
            }
        }
        
        if(isNeg){
            result="-";
        }
        
        //this will store the value represented by input
        int val=0; 
        //calculate the value represented by input using base1
        int len = input.length();
        for (int i = len-1,j=0; i>=0; i--,j++){
            int inputCharVal = Integer.valueOf(input.substring(j, j+1));
            val+=inputCharVal*Math.pow(base1, i);
        }
        
        // now convert val to a string using base2
        String nval="";
        while(val>0){
            //use the mod to find the right most digit
            int rightSide = val%base2;
            nval=rightSide+nval;
            //use the quotient to find the other digits
            val = val/base2;
        }
        
        result += nval;
        
        return result;
    }
    
}
