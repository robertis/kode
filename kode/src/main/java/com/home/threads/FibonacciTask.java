/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.threads;

import java.util.concurrent.RecursiveTask;

/**
 *
 * 
 */
public class FibonacciTask extends RecursiveTask<Integer>{

    int n;
    public FibonacciTask(int num) {
        this.n = num;
    }
    
    
    @Override
    protected Integer compute() {
        if(n <= 1){
            return n;
        }
        FibonacciTask f1 = new FibonacciTask(n-1);
        f1.fork();
        
        FibonacciTask f2 = new FibonacciTask(n-2);
        
        int result = f2.compute()+f1.join();
        
        return result;
    }
    
}
