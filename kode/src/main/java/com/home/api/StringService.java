/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author rtongbram
 */
public class StringService {
    
    
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
            if(token.equalsIgnoreCase("..")){
                // if you come across "..", pop if stack is not empty
                // otherwise ignore it
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
            else if(token.equalsIgnoreCase(".")){
                //ignore it
            }
            else if(token.equalsIgnoreCase("")){
                //ignore it
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
    
    
}
