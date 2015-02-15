/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author rtongbram
 */
public class ConcurrentCache<K, V> {

    private int CAPACITY = 16;
    private Map<K, Node> hm = null;
    Queue<Node> queue = null;
    
    public ConcurrentCache(int capacity) {
        this.CAPACITY = capacity;
        hm = new ConcurrentHashMap<>(this.CAPACITY);
        queue = new ConcurrentLinkedQueue<>();
    }

    public void set(K key, V value) {
        if (hm.size() >= this.CAPACITY) {
            //remove the oldest element.
            Node<K, V> oldestElem = queue.poll();
            hm.remove(oldestElem.key);
        }

        Node<K, V> node = new Node<>(key, value);
        hm.put(key, node);
        queue.add(node);
    }

    public V get(K key) {
        V result = null;
        Node<K, V> node = hm.get(key);
        //move the node in the list to the tail of the queue.
        if (node != null) {
            queue.remove(node);
            queue.add(node);
            result = node.data;
        }
        return result;
    }

    private class Node<K, V> {
        public K key;
        public V data;
        public Node(K key, V data) {
            this.key = key;
            this.data = data;
        }
    }
}
