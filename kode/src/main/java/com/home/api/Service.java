/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Service {
    
    //return the smallest substring from bigStr that has all the chars from sub
    // ADOBECODEBANC and ABC returns BANC
    public static String minimumWindowSubstring(String bigStr, String sub){
        String result = bigStr;
        int len = bigStr.length();
        // store the chars of sub in a hashMap
        Set<String> charMap = new HashSet<String>();
        HashMap<String,Integer> bigMap = new HashMap<>();
        for(int i = 0 ; i< sub.length(); i++){
            charMap.add(sub.substring(i, i+1));
        }
        int i =0, j =0;
        
        while (j < len){
            // move j on the right until it covers all the elements in sub/charMap
            while(bigMap.size() < charMap.size()){
                String theStr = bigStr.substring(j, j+1);
                if(charMap.contains(theStr)){
                    if(bigMap.containsKey(theStr)) bigMap.put(theStr, bigMap.get(theStr)+1);
                    else bigMap.put(theStr, 1);
                }
                j++;
            }
            
            // once covered compare and record the substring which is bigStr.substring(i,j+1)
            if(bigStr.substring(i, j).length() < result.length()){
                result = bigStr.substring(i,j);
            }
            
            // once the elements are covered. move i on the right,removing from bigMap 
            // until the elements are not covered again
            while(bigMap.size()==charMap.size()){
                
                String theStr = bigStr.substring(i, i+1);
                if(charMap.contains(theStr)){
                    if(bigMap.containsKey(theStr)) {
                        int val = bigMap.get(theStr);
                        val--;
                        if(val==0){
                            bigMap.remove(theStr);
                        }
                        else{
                            bigMap.put(theStr, val);
                        }
                        
                    }
                }
                // move i to the right 
                // find the next element which is in charmap
                i++;
                String nextStr = bigStr.substring(i, i+1);
                while(!charMap.contains(nextStr)){
                    i++;
                    nextStr = bigStr.substring(i, i+1);
                }
            }
            
        }
        return result;
    }
    
    // return the starting index from which all the stations can be reached
    // otherwise return -1.
    public static int gasStation(int[] gas,int[]distance){
        int startIndex = -1;
        int total = 0 ;
        int sum = 0;
        int len = gas.length;
        for (int i =0; i< len-1 ; i++){
            sum += (gas[i]-distance[i]);
            total+=sum;
            if(sum < 0){
                sum = 0;
                startIndex = i+1;
            }
        }
        
        if(total < 0){
            startIndex = -1;
        }
        return startIndex;
    }
    
    // 1, 11, 21, 1211, 111221
    public static String countAndSay(int n){
        String result =Integer.toString(1);
        
        for(int i =0; i< n-1 ; i++){
            result = nextCountAndSay(result);
        }
        
        return result;
    }
    
    public static String nextCountAndSay(String s){
        String result = "";
        int len = s.length();
        for (int i = 0 ; i< len; i++){
            int count =1;
            while((i < len-1) && (s.charAt(i)==s.charAt(i+1))){
                count++;
                i++;
            }
            result +=count+s.substring(i, i+1);
        }
        
        return result;
    }
    
    // viii=8, ix = 9
    public static int romanToInt(String data){
        int result=0;
        HashMap<String,Integer> mappings = new HashMap();
        mappings.put("I", 1);
        mappings.put("V", 5);
        mappings.put("X", 10);
        mappings.put("L", 50);
        mappings.put("C", 100);
        mappings.put("D", 500);
        mappings.put("M", 1000);
        if(data!=null && data.length() > 0){
            for(int i =0; i< data.length()-1; i++){
                String rch = data.substring(i, i+1);
                String nextCh = data.substring(i+1, i+2);
                if(mappings.containsKey(rch) && mappings.containsKey(nextCh)){
                    int currVal = mappings.get(rch);
                    int nextVal = mappings.get(nextCh);
                    if(currVal >= nextVal){
                        result += currVal;
                    }
                    else{
                        result -=currVal;
                    }
                    
                }
                else{
                    throw new RuntimeException("unsupported chars");
                }
            }
            result+=mappings.get(data.substring(data.length()-1, data.length()));
        }
        
        return result;
    }
    
    
    // convert integer values like 10 to X and 6 to VI
    // 9 to IX
    // eg value = 14
    public static String integerToRoman(int value){
        String result="";
        String [] roman = {"M","CM","D","CD", "C","XC", "L","XL", "X","IX", "V","IV","I"};
        int [] num = {1000,900,500,400, 100,90, 50,40, 10,9, 5,4,1};
        int len = roman.length;
        int i =0;
        while(i < len){
            if(value < num[i]){
                i++;
            }
            else{
                value -=num[i];
                result +=roman[i];
            }
        }
        return result;
    }
    
    //Given an array A of integers and k, return a subset of size k
    // the subset should be equally likely
    public static int[] sampleOfflineData(int[] arr, int k){
        int[] result = new int[k];
        int n = arr.length;
        // get a random number between 1 to n and swap the values arr[rand] and arr[n]
        Random rgen = new Random();
        
        while(k>0){
            int rand = rgen.nextInt(n);
            //swap the numbers
            int tmp = arr[rand];
            arr[rand]=arr[n-1];
            arr[n-1]=tmp;
            n--;
            k--;
        }
        result = Arrays.copyOfRange(arr, n, arr.length);
        return result;
    }
    
    public static int[] reservoirSampling(int[] input, int k){
        int [] result = new int[k];
        
        Random rgen = new Random();
        int n = input.length;
        
        for(int i =0; i< n; i++){
            int data = input [i];
            if(i<k){
                result[i]=data;
            }
            else{
                int rand = rgen.nextInt(i);
                if(rand < k){
                    result[rand]=data;
                }
            }
        }
        
        return result;
    }
    
    public static int[] reservoirSampling1(InputStream is, int k) throws IOException{
        int [] result = new int[k];
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while(br.ready()){
            int val = br.read();
            System.out.println("found a char ="+val);
        }
        
        return result;
    }
    // return the amount of water trapped.
    public static int getMost(int[] values){
        
        int result = 0;
        int[] leftMax = new int[values.length];
        int[] rightMax = new int[values.length];
        
        for (int i = 1; i< values.length; i++){
            leftMax[i] = Math.max(leftMax[i-1], values[i-1]);
        }
        
        for (int i = values.length-2; i>= 0; i--){
            rightMax[i] = Math.max(rightMax[i+1],values[i+1]);
        }
        
        for(int i = 1; i< values.length-1; i++){
            if(values[i] < Math.min(leftMax[i], rightMax[i])){
                result += Math.min(leftMax[i], rightMax[i]) - values[i];
            }
        }
        return result;
    }
    
}
