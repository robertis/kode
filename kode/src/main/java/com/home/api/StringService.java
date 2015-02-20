/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rtongbram
 */
public class StringService {
    
    public static void main(String args[]){
        String haystack = "ABABACABABABCABABABCABABACABABABC";
        //haystack = "";
        String needle = "ABABAC";
        System.out.println("found ="+strStr(haystack,needle));
    }
    /****
     * Find if needle is found in haystack
     * 
     * Brute force search
     * 
     ****/
    public static String strStr(String haystack, String needle){
        
        if(haystack == null || haystack.isEmpty() || 
                needle==null || needle.isEmpty() ||
                needle.length() > haystack.length()){
            return null;
        }
        
        int h =0; 
        while(h < haystack.length()){
            int hst=h;
            int ndl=0;
            while(ndl<needle.length() && haystack.charAt(hst)==needle.charAt(ndl)){
                hst++;
                ndl++;
            }
            if(ndl==needle.length()){
                return haystack.substring(h);
            }
            else{
                h++;
            }
        }
        
        
        return null;
    }
    
    /*
    private int[] kpmPrefix (String input){
        int[] prefix = new int [input.length()];
        
        prefix[0]=0;
        int k = 0;
        for (int j = 1; j <input.length(); j++){
            
        }
        
        return prefix;
    }
    */
    
    // find regex matching for:
    // '.' : match any single character
    // '*' : match 0 or more of the preceding element
    public static boolean isMatch(String input, String pattern){
        
        //1. Check for null and empty conditions
        if(input==null){
            return (pattern==null);
        }
        if(input.isEmpty()) return pattern.isEmpty();
        if(pattern !=null){
            if(input==null) return false;
        }
        else{
            return (input==null);
        }
        if(input.length()==1){
            
        }
        
        //2. if the 2nd char ( next char ) is not '*'
        if(pattern.length() > 1 && pattern.charAt(1)!='*' && input.length() > 1){
            if((pattern.charAt(0) == input.charAt(0)) || (pattern.charAt(0)=='.' && input.length()>0)){
                return isMatch(input.substring(1), pattern.substring(1));
            }
        }
        
        
        // 3. if the 2nd char ( next char ) is '*'
        while(( pattern.charAt(0)==input.charAt(0)) || (pattern.charAt(0) == '.' && input.length() > 0)){
            // trying the zero match for '*'
            if( pattern.length() > 2 && isMatch(input, pattern.substring(2))){
                return true;
            }
            // preparing for the next comparision for 1 or more match of '*', in the next while loop
            // this will make sure of the 1 or mor match of '*'
            input=input.substring(1);
        }
        
        if( pattern.length() > 2 && isMatch(input, pattern.substring(2))){
            return true;
        }
        return false;
    }
    
    
    /*
     *********************************************************************
     * 
     *   /../a/b => if it starts with '/', append it to result
     *   ../../../ab => if the input starts with .. ignore them.
     *   ./././ab => if the input starts with . ignore them.
     *   aa/./bb => if . follows a str, ignore the ., always ingore the .
     *   aa/../bb=> put aa in stack, if you meet .., remove aa from stack.
     *   a//b => same as a/b
     * 
     ***********************************************************************
     */
    public static String simplifyIt(String input){
        String result ="";
        Deque<String> stack = new ArrayDeque<>();
        if(input == null || input.isEmpty()) return input;
        
        if (input.startsWith("/")){
            result ="/";
            input = input.substring(1);
        }
        
        String[] tokens = input.split("/");
        
        for(String token : tokens){
            if(token.equalsIgnoreCase("..") && !stack.isEmpty()){
                stack.pop();
            }
            
            else{
                // char string
                stack.push(token);
            }
        }
        
        while(!stack.isEmpty()){
            result+=stack.removeLast();
            result+="/";
        }
        
        return result;
    }
    
    /*
     *********************************************************************
     * Given a mapping : A->1, B-2, C->3... Z->26
     * Find the number of ways a string representing a number can be decoded.
     * Eg : 12 => AB or L, so the result for 12 as input, is 2.
     * 
     * Iterate the input string. 
     * Number of ways at char i = num ways at i-1 (considering arr[i] separately)
     *  + num ways at i-2 ( considering arr[i-1,i] as one encoding, if arr[i-1,i] 
     * has a mapping.
     * numWays[i] = numWays[i-1]+ (if arr[i-1,i] has a mapping) numWays[i-2]
     * 
     ***********************************************************************
     */
    public static int numDecodings(String input ){
        
        Map<String,String> mappings = getNumCharMappings();
        int[] numWays = new int [input.length()];
        if(input == null || input.isEmpty()){
            return 0;
        }
        if(input.length()==1){
            if(mappings.containsKey(input.substring(0))){
                return 1;
            }
            else{
                return 0;
            }
                  
        }
        String firstChar = input.substring(0,1);
        String secondChar = input.substring(1,2);
        if(mappings.containsKey(firstChar)){
            numWays[0]=1;
        }
        numWays[1]=0;
        if(mappings.containsKey(secondChar)){
            numWays[1]=numWays[0];
        }
        if (mappings.containsKey(input.substring(0, 2))) {
            numWays[1]++;
        }
        for(int i = 2 ; i < input.length(); i++){
            if(mappings.containsKey(input.substring(i, i+1))){
                numWays[i]=numWays[i-1];
            }
            if(mappings.containsKey(input.substring(i-1,i+1))){
                numWays[i]+=numWays[i-2];
            }
        }
        
        return numWays[input.length()-1];
    }
    
    private static Map<String,String> getNumCharMappings (){
        Map<String,String> numCharMappings = new HashMap<>();
        
        numCharMappings.put("1", "A");
        numCharMappings.put("2", "B");
        numCharMappings.put("3", "C");
        numCharMappings.put("4", "D");
        numCharMappings.put("5", "E");
        numCharMappings.put("6", "F");
        numCharMappings.put("7", "G");
        numCharMappings.put("8", "H");
        numCharMappings.put("9", "I");
        numCharMappings.put("10", "J");
        numCharMappings.put("11", "K");
        numCharMappings.put("12", "L");
        numCharMappings.put("13", "M");
        numCharMappings.put("14", "N");
        numCharMappings.put("15", "O");
        numCharMappings.put("16", "P");
        numCharMappings.put("17", "Q");
        numCharMappings.put("18", "R");
        numCharMappings.put("19", "S");
        numCharMappings.put("20", "T");
        numCharMappings.put("21", "U");
        numCharMappings.put("22", "V");
        numCharMappings.put("23", "W");
        numCharMappings.put("24", "X");
        numCharMappings.put("25", "Y");
        numCharMappings.put("26", "Z");
        
             
        return numCharMappings;
    }
    
}
