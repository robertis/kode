/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rtongbram
 */
public class PermutationService {
    
    
    public static void main(String args[]){
        int[] values = {1,2,3,4,5,6};
        subsetSum2(values, 6);
        subsetSum(values, 6);
        //int[] vals = {1,2,3};
        //subsetAll(vals);
        //List<String> result = getSubsets("abcd");
        //System.out.println(result);
    }
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
    
    
    public static void subsetSum(int[] values, int target){
        List<Integer> valueList = new ArrayList<>();
        for(int value : values){
            valueList.add(value);
        }
        List<Integer> soFar = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        subsetSumRec(soFar, valueList, target, 0, result);
        
        for(List<Integer> list : result){
            System.out.println("list = "+list);
        }
    }
    
    public static void subsetSumRec(List<Integer> soFar, List<Integer> rest, 
            int target, int index, List<List<Integer>> result){
        if(target==0){
            result.add(soFar);
            return;
        }
        else{
            for(int i = index ; i < rest.size(); i++){
                List<Integer> soFarTmp = new ArrayList<>(soFar);
                soFarTmp.add(rest.get(i));
                List<Integer> restTmp = new ArrayList<>(rest);
                restTmp.remove(i);
                subsetSumRec(soFarTmp,restTmp,target-rest.get(i), i, result);
            }
            
            
        }
    }
    
    public static void subsetSum2(int[] values, int target){
        List<Integer> valueList = new ArrayList<>();
        for(int value : values){
            valueList.add(value);
        }
        List<Integer> soFar = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        subsetSumRec2(soFar, valueList, target, result);
        
        for(List<Integer> list : result){
            System.out.println("list 2 = "+list);
        }
    }
    
    public static void subsetSumRec2(List<Integer> soFar, List<Integer> rest, 
            int target,  List<List<Integer>> result){
        if(rest.isEmpty()){
            if(target==0){
                result.add(soFar);
            }
            
            return;
        }
        else{
            List<Integer> soFarTmp = new ArrayList<>(soFar);
            soFarTmp.add(rest.get(0));
            List<Integer> restTmp = new ArrayList<>(rest);
            restTmp.remove(0);
            subsetSumRec2(soFarTmp, restTmp, target - rest.get(0), result);
            subsetSumRec2(soFar, restTmp, target, result);
        }
    }
    
    
    
    public static void subsetAll(int[] values){
        List<Integer> valueList = new ArrayList<>();
        for(int value : values){
            valueList.add(value);
        }
        List<Integer> soFar = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        subsetAllRec(soFar, valueList, result);
        
        for(List<Integer> list : result){
            System.out.println("list = "+list);
        }
    }
    
    public static void subsetAllRec(List<Integer> soFar, List<Integer> rest, 
              List<List<Integer>> result){
        if(rest.isEmpty()){
            
            if(!soFar.isEmpty()){
                result.add(soFar);
            }
            
            return;
        }
        else{
            List<Integer> soFarTmp = new ArrayList<>(soFar);
            soFarTmp.add(rest.get(0));
            List<Integer> restTmp = new ArrayList<>(rest);
            restTmp.remove(0);
            subsetAllRec(soFar, restTmp, result);
            subsetAllRec(soFarTmp, restTmp, result);
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
