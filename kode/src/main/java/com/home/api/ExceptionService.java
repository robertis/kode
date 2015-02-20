/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

/**
 *
 * @author rtongbram
 */
public class ExceptionService {
    public static void main(String args[]){
        try{
            testRTException();
        }
        catch(Exception ex){
            
        }
        
    }
    public static void testException() throws Exception{
        System.out.println("test exception");
        throw new Exception();
    }
    
    public static void testRTException() throws RuntimeException{
        System.out.println("testRTE");
        throw new RuntimeException();
    }
    
    public static void testNPException() throws NullPointerException{
        System.out.println("testNPE");
        throw new NullPointerException();
    }
    
    
    
}
