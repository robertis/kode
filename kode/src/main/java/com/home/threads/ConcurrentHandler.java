/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.threads;

import com.home.gen.RingBufferBlocking;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rtongbram
 */
public class ConcurrentHandler {
    
    private static BlockingQueue queue = new ArrayBlockingQueue(1024);
    private static RingBufferBlocking<Integer> buffer = new RingBufferBlocking(1024);
    public static void main(String args[]){
        //testRecursiveTask();
        runQueue();

    }
    
    private static void testRecursiveTask(){
        FibonacciTask task = new FibonacciTask(6);
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("fib 5="+pool.invoke(task));
    }
    
    private static void testFutureTask(){
        System.out.println("Inside futuremain");
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                Random rand = new Random();
                int sum=0;
                for(int i =0; i< 10; i++){
                    Thread.sleep(1000);
                    System.out.println("................"+i);
                    sum+=rand.nextInt();
                }
                return "Callable Result="+sum;
            }});
        executorService.execute(futureTask);
        try {
            System.out.println("Do work 1");
            Thread.sleep(1000);
            System.out.println("Do work 2");
            Thread.sleep(1000);
            System.out.println("will cancel now");
            Thread.sleep(1000);
            boolean cancelled = futureTask.cancel(true);
            if(cancelled){
                System.out.println("cancelled");    
            }
            else{
                System.out.println("Now will wait for the future task result");
                System.out.println("Future result = "+futureTask.get());
            }
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ConcurrentHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ConcurrentHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        executorService.shutdown();
        
    }
    
    
    
    
    private static void testFuture(){
        System.out.println("Inside futuremain");
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(new Callable<String>() {
            public String call() throws Exception {
                Random rand = new Random();
                int sum=0;
                for(int i =0; i< 10; i++){
                    Thread.sleep(1000);
                    System.out.println("................"+i);
                    sum+=rand.nextInt();
                }
                return "Callable Result="+sum;
            }
        });
        try {
            System.out.println("Do work 1");
            Thread.sleep(1000);
            System.out.println("Do work 2");
            Thread.sleep(1000);
            System.out.println("will cancel now");
            Thread.sleep(1000);
            boolean cancelled = false;
            //cancelled = future.cancel(true);
            if(cancelled){
                System.out.println("cancelled");    
            }
            else{
                System.out.println("Now will wait for the future  result");
                System.out.println("Future result = "+future.get());
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ConcurrentHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ConcurrentHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        executorService.shutdown();
    }
    
    
    private static void runQueue(){
        System.out.println("Inside futuremain");
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future futureProducer = executorService.submit(new Callable<String>() {
            public String call() throws Exception {
                for(int i =0; i< 10; i++){
                    System.out.println(".....producer...........inserting .. "+i*100);
                    //queue.put(i*100);
                    buffer.enqueue(i*100);
                    Thread.sleep(1000);
                }
                return "producer all done";
            }
        });
        
        Future futureConsumer = executorService.submit(new Callable<String>() {
            public String call() throws Exception {
                int sum=0;
                for(int i =0; i< 10; i++){
                    //System.out.println(".....consumer...........found = "+queue.take());
                    System.out.println(".....consumer...........found = "+buffer.dequeue());
                    
                }
                return "consumer all done ";
            }
        });
        
        try {
            System.out.println("Future producer result = "+futureProducer.get());
            System.out.println("Future consumer result = "+futureConsumer.get());
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ConcurrentHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ConcurrentHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        executorService.shutdown();
    }
    
    
}
