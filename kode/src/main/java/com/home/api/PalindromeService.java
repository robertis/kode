/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rtongbram
 */
public class PalindromeService {

    
    public static void main(String args[]){
        String input =  "abadef";
        int result = 0;
        //result = minimumPalindromes(input);
        //System.out.println("Result 1 = "+result);
        result = minimumPalindromesDyPr(input);
        System.out.println("Result 2= "+result);
    }
    //mp[i][j] containes the min palindrom decompostion for the substring(i,j+1)
    // we want to return mp[0][input.length()]
    public static int minimumPalindromesDyPr(String input){
        int result = input.length();
        int len = input.length();
        int [][] mp = new int[input.length()][input.length()];
        //mp[0][0]=1;
        //set single char entries to 1 as all 1 char word is palindrome
        for (int i=0;i<len;i++ ){
            mp[i][i]=1;
        }
        for (int i=0;i<len-1;i++ ){
            if(input.charAt(i)==input.charAt(i+1)){
                mp[i][i+1]=1;
            }
            else{
                mp[i][i+1]=2;
            }
        }
        
        for(int i =0; i <len-2; i++){
            for (int j=i+2;j< len;j++){
                //if input.substring(i,j+1) is palin, mp[i][j]=1
                if((mp[i+1][j-1]==1) && (input.charAt(i)==input.charAt(j))){
                    mp[i][j]=1;
                }
                // else min(mp[i][k]+mp[k][j],j-i+1)
                else{
                    mp[i][j]=j-i+1;
                    for(int k =i+1 ; k<j;k++){
                        mp[i][j]=Math.min(mp[i][k]+mp[k][j],mp[i][j]);
                    }
                }
                
            }
        }
        
        return mp[0][len-1];
    }
    
    public static int minimumPalindromes(String input){
        int result =0;
        //List<String> result= new ArrayList<>();
        int start =0, end = input.length()-1;
        int i=0, j = input.length()-1;
        while(start<end && input.charAt(start)==input.charAt(end)){
            start++;
            end--;
        }
        // if a palindrome is possible with this input string, return 1 ( min num)
        if(end<=start){
            return 1;
        }
        //otherwise, find the min num of palindromes formed by diff substrings
        
        //set result to length of the input string, because each letter/char is a palindrome.
        // an n-char string has 4 palindromes of 1 char each.
        result  = j-i+1;
        
        for(int k=i; k<j ; k++){
            String leftSub = input.substring(i, k+1);
            String rightSub =input.substring(k+1, j+1);
            int minP = minimumPalindromes(leftSub) +
                    minimumPalindromes(rightSub);
            
            result = Math.min(result, minP);
        }
        return result;
        
    }
    
}
