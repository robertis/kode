/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.gen;

import java.util.Arrays;

/**
 *
 *
 */
public class RingBuffer<E> {
    
    private int start, end, capacity, currSize;
    private E[] elements;

    public RingBuffer(int size) {
        this.capacity = size;
        start = 0;
        end =0;
        currSize = 0;
        elements = (E[]) new Object[size];
    }
    
    
    
    /**
     * Keep adding elements in the end of the array
     * - if(currSize == capacity) remove 1 element at START
     * - add the elem at END
     *
     **/
    
    
    public void enqueue(E elem){
        if(currSize >= capacity){
            E e = this.dequeue();
        }
        
        elements[end] = elem;
        end = (end+1)%capacity;
        currSize++;
        
    }
    
    
    /**
     * Remove elem from start index
     * 
     **/
    public E dequeue(){
        E elem = elements[start];
        elements[start] = null;
        start = (start + 1) % capacity;
        currSize--;
        return elem;
    }
    
    public void printElements(){
        System.out.println(Arrays.toString(this.elements));
    }
}
