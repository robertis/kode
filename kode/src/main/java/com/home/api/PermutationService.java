/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rtongbram
 */
public class PermutationService {
    
    //given a string of numbers, return all the possible outcomes of combinatons of strings
    /*
     * Mappings :
     * 2 - ABC
     * 3 - DEF
     * 4 - GHI
     * 5 - JKL
     * 6 - MNO
     * 7 - PQR
     * 8 - STU
     * 9 - VWXZ
     */
    // for input 234 , result can contain (ADH, ADI, etc).
    public static List<String> listPhoneMnemonics(String phoneNum){
        List<String> result = new ArrayList<>();
        Map<String,String> hm = new HashMap<>();
        hm.put("2", "ABC");
        hm.put("3", "DEF");
        hm.put("4", "GHI");
        hm.put("5", "JKL");
        hm.put("6", "MNO");
        hm.put("7", "PQRS");
        hm.put("8", "TUV");
        hm.put("9", "WXYZ");
        
        phoneRec("",phoneNum, result,hm);
        return result;
    }
    
    private static void phoneRec(String soFar,String rest, List<String> result, Map<String, String> hm){
        if(rest.isEmpty()){
            result.add(soFar);
            return;
        }
        String curr = rest.substring(0,1);
        String chars = hm.get(curr);
        for(int i =0 ; i < chars.length(); i++){
            String mp = chars.substring(i,i+1);
            phoneRec(soFar+mp,rest.substring(1), result, hm);
        }
        
    }
    
    public static List<String> getAllPermutations(String input){
        List<String> result = new ArrayList<>();
        permutationsRec("", input, result);
        return result;
    }
    
    private static void permutationsRec(String soFar, String rest, List<String> result){
        
        if(rest.isEmpty()){
            result.add(soFar);
        }
        for (int i = 0; i < rest.length(); i++){
            String remaining = rest.substring(0,i)+rest.substring(i+1);
            permutationsRec(soFar+rest.substring(i,i+1), remaining, result);
        }
    }
    
    
    public static List<String> getSubsets(String input){
        List<String> result = new ArrayList<>();
        subsetsRec("", input, result);
        return result;
    }
    
    // call twice 
    // subsetsRec(soFar+rest[0], rest[1:len], result)
    // subsetsRec(soFar, rest[1:len], result)
    private static void subsetsRec(String soFar, String rest, List<String> result){
        
        if(rest.isEmpty()){
            result.add(soFar);
        }
        else {
            String remaining = rest.substring(1);
            subsetsRec(soFar + rest.substring(0, 1), remaining, result);
            subsetsRec(soFar, remaining, result);
        }
     
    }
}
