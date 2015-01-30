/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

/**
 * input = 1362
 * div = 1000
 * 
 */
public class NumHelper {
    
    public static boolean checkIntPal(int input){
        if(input<10) return true;
        int val = input;
        int rightNum = 0, leftNum = 0;
        int div= 1;
        while(input/10 > div){
            div *=10;
        }
        while(val > 9){
            rightNum = val%10;
            leftNum = val/div;
            if(leftNum!=rightNum) return false;
            
            //remove leftmost digit
            val=val%div;
            //remove rightmost digit
            val=val/10;
            div /=100;
            
        }
        return true;
    }
}
