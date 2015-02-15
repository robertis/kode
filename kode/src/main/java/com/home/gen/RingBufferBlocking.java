/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.gen;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rtongbram
 */
public class RingBufferBlocking<E> {

    private int start, end, capacity, currSize;
    private E[] elements;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public RingBufferBlocking(int capacity) {
        this.capacity = capacity;
        start = 0;
        end = 0;
        currSize = 0;
        elements = (E[]) new Object[capacity];

    }

    public void enqueue(E elem) throws InterruptedException {
        lock.lock();
        try {
            while (currSize >= capacity) {
                notFull.await();
            }
            elements[end] = elem;
            end = (end + 1) % capacity;
            currSize++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }

    }

    public E dequeue() throws InterruptedException {
        lock.lock();
        E e;
        try {
            while (currSize == 0) {
                notEmpty.await();
            }
            e = elements[start];
            start = (start + 1) % capacity;
            notFull.signal();
            currSize--;
            
        } finally {
            lock.unlock();
        }

        return e;
    }
}
