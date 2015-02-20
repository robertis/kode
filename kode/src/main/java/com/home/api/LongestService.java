/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rtongbram
 */
public class LongestService {
    
    
    //[3,-2,7,9,8,1,2,0] => 4, range is [0,1,2,3]
    //find the numbers that are consecutive in the list
    // they need not be together in the input list
    /*
     * Store the numbers in a hashmap. And for each number, find the numbers above and below it
     * keep track of the max count.
     */
    public static int longestConsecutiveSequece(int[] input){
        int result=0;
        // Store the numbers in a hashmap 
        Set<Integer> hs = new HashSet<>();
        for(int v : input){
            hs.add(v);
        }
        // for each number in the input array, we check :
        // 1. how many other consecutive numbers are there below it
        // 2. how many other consecutive numbers are above it.
        // we add these 2 values along with 1 and keep recording the max.
        for(int val : input){
            if(hs.contains(val)){
                int consecutiveNumbers = 1+findConsecutiveNumbers(val,hs,true)+
                    findConsecutiveNumbers(val,hs,false);
                result = Math.max(consecutiveNumbers, result);
            }
            
        }
        
        
        return result;
    }
    
    private static int findConsecutiveNumbers(int num, Set<Integer> hs, boolean isBelow){
        int count =0;
        if(isBelow)num--;
        else num++;
        while(hs.contains(num)){
            hs.remove(num);
            count++;
            if(isBelow)num--;
            else num++;
        }
        
        return count;
    }
    
    //if input is ")()())", output is 4, as the longest valid 
    // parentheseis is "()()"
    // "(())" => 4
    public static int longesValidParentheses(String input){
        int result = 0;
        // keep scanning the string
        int len = input.length();
        int startIndex = -1;
        
        Deque<Integer> stk = new ArrayDeque<>();
        // if "(" , push it to stack, we never push ")" to stack
        // we only push the index of "(" to the stack
        
        for(int i =0; i< len;i++){
            if(input.charAt(i) == '('){
                //push the index to stack
                stk.push(i);
            }
            else if(input.charAt(i) == ')'){
                //check if an item "(" is there in the stack
                if(stk.isEmpty()) {
                    // earlier startIndex was -1, while starting out
                    // now when the incoming string is ')' and stack is empty
                    // the current position i is same as -1 when starting out.
                    // it means we are starting a new string all over.
                    startIndex = i;
                }
                else if(!stk.isEmpty())
                {
                    // stack is not empty, pop out one elem.
                    stk.pop();
                    int currLen;
                    if(stk.isEmpty()){
                        // if after popping out, all elems are gone,
                        // find len by i-startIndex
                        currLen = i-startIndex;       
                    }
                    else{
                        // if there are some elems, len is i-stack.top
                        // since we pushed the index when we got '('
                        currLen = i-stk.peek();
                    }
                    result = Math.max(currLen, result);
                }
            }
        }
        
        // if")" , check if stack is not empty and stack.top is ")"
        
        // if the stack is not empty, pop out an element 
        // after popping out, if the stack is empty len=curr-lastIndex+1
        // else len = curr-stack.top()
        
        return result;
    }
    
    /**
     * - create a prefix sum array prefix_sum[i] = arr[i]+prefix_sum[i-1]
     * - create a min_prefix_sum arr by :
     *      min_prefix_sum[i] = min(prefix_sum[i], min_prefix_sum[i+1])
     * 
     * - set i =0, j=0 and compare min_prefix_sum[j]-prefix_sum[i] < = k
     *  and keep incrementing j, otherwise increment i
     * - set result as len = max(len, j-i)
     * 
     * 
     */
    public static int[] longestSubarrayLessThanK(int[] values, int k){
        int arrLen = values.length;
        
        int[] prefixSum = new int[arrLen];
        int[] minSum = new int[arrLen];
        prefixSum[0]=values[0];
        for (int i = 1; i< arrLen; i++){
            prefixSum[i]=values[i]+prefixSum[i-1];
        }
        
        minSum[arrLen-1]=prefixSum[arrLen-1];
        for(int i=arrLen-2; i>=0; i--){
            minSum[i]= Math.min(prefixSum[i], minSum[i+1]);
        }
        int i=-1, j=0;
        int maxLen = 0;
        int startIndex =0, endIndex=0;
        int prefixVal = 0;
        System.out.println(Arrays.toString(prefixSum));
        System.out.println(Arrays.toString(minSum));
        
        while(j<arrLen){
            int diffVal = minSum[j]-prefixVal;
            
            // if diff < k, try to expand by incrementing j
            if(diffVal <= k){
                j++;
            }
            else{
                // else increment i so we get a new diff val
                i++;
                prefixVal = prefixSum[i];
            }
            
            // check if the new diff is larger than result, and store it.
            if(j-i > maxLen){
                maxLen = j-i;
                startIndex = i;
                endIndex = j;
            }
        }
        
        int[] result = Arrays.copyOfRange(values, startIndex+1, endIndex);
        return result;
    }
}
