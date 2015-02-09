/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.gen;

/**
 *
 * 
 */
public class Lazy {
    
    private Lazy(){
        
    }
    
    private static class LazyHolder{
        private static final Lazy INSTANCE = new Lazy();
    }
    
    public static Lazy getInstance(){
        return LazyHolder.INSTANCE;
    }
}
