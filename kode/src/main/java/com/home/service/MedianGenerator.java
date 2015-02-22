/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author rtongbram
 */
public class MedianGenerator {

    int median = 0;
    int SIZE = 16;
    Queue<Integer> minQueue;
    Queue<Integer> maxQueue;

    public MedianGenerator() {
        minQueue = new PriorityQueue<>(SIZE, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n1 - n2;
            }
        });

        maxQueue = new PriorityQueue<>(SIZE, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n2 - n1;
            }
        });

    }

    public double getData() {

        if (minQueue.isEmpty() && maxQueue.isEmpty()) {
            throw new NoSuchElementException("no elements yet");
        } else if (minQueue.isEmpty()) {
            return maxQueue.peek();
        } else if (maxQueue.isEmpty()) {
            return minQueue.peek();
        }

        if (maxQueue.size() > minQueue.size()) {
            return maxQueue.peek();
        } else if (maxQueue.size() < minQueue.size()) {
            return minQueue.peek();
        } else {
            double val = (double) (maxQueue.peek() + minQueue.peek()) / 2;
            return val;
        }
    }

    /*
     *******************************************************************************
     * - if both are empty queues, put in minQueue (or the other)
     * - if elem is smaller than the head of the maxQueue, put it in maxQueue
     * - if its bigger than the head of minQueue, put it in minQueue
     * - otherwise put it in the smaller queue.
     * - adjust size in both sides, if one is bigger by more than 1, remove and 
     *   insert to the other one.
     *
     **********************************************************************************
     */
    public void putData(int data) {
        if (minQueue.isEmpty() && maxQueue.isEmpty()) {
            minQueue.add(data);
        } else if (!maxQueue.isEmpty()) {
            if (maxQueue.peek() > data) {
                maxQueue.add(data);
            } else {
                minQueue.add(data);
            }
        } else if (!minQueue.isEmpty()) {
            if (minQueue.peek() < data) {
                minQueue.add(data);
            } else {
                maxQueue.add(data);
            }
        }
        // adjust size 
        while (minQueue.size() < maxQueue.size()) {
            minQueue.add(maxQueue.remove());
        }
        while (minQueue.size() > maxQueue.size()) {
            maxQueue.add(minQueue.remove());
        }

    }

    public void printAll() {
        System.out.println(Arrays.toString(minQueue.toArray()));
        System.out.println(Arrays.toString(maxQueue.toArray()));
    }
}
