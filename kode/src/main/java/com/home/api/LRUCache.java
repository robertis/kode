/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Use a HashMap to maintain the data and a linked list to maintain the keys
 * 
 */
public class LRUCache{
    
    private int CAPACITY=16;
    private Map<String, Node> hm = null;
    LinkedList list = new LinkedList();
    
    public LRUCache(int capacity){
        this.CAPACITY = capacity;
        hm = new HashMap<>(this.CAPACITY);
    }
    
    public void  set(String key, Object elem){
        
        Node n = new Node(elem);
        n.key = key;
        
        // if size >= capacity, evict the oldest entry.
        if(hm.size() >= CAPACITY){
            Node lruNode = list.removeTail();
            hm.remove(lruNode.key);
        }
        
        if(hm.containsKey(key)){
            Node node = hm.get(key);
            // remove it from the linked list
            list.removeNode(node);
        }
        
        //add the node in the map and the linked list
        hm.put(key, n);
        list.insertNode(n);
        
    }
    
    public Object get(String key){
        Node n = hm.get(key);
        Object result = null;
        if(n!=null && n.data!=null){
            result = n.data;
            list.moveToFront(n);
        }
        
        return result;
    }
    
    
    /*
     * *********************************************************************
     * Node class for the linked list
     * inner class to represent a linked list node
     * *********************************************************************
     */
    
    private class Node {
        public String key;
        public Object data;
        public Node next;
        public Node prev;
        public Node(Object data){
            this.data = data;
        }
    }
    
    /*
     * *********************************************************************
     * Doubly linked list class
     * inner class to represent a linked list
     * 
     * The oldest node ( least recently used) is at the tail
     * The most recently accessed or inserted node is 
     * at the head.
     * 
     * Doubly linked list is needed because we will need to remove nodes
     * from the list , anywhere in the list, we need to have a pointer to 
     * the previous node.
     * ********************************************************************
     */
    private class LinkedList{
        private Node head;
        private Node tail;
        public LinkedList(){
            head = null;
            tail = null;

        }
        
        public void removeNode(Node node){
            if(node!=head){
                node.prev.next=node.next;
                node.next.prev = node.prev;
                if (node == tail) {
                    tail = node.prev;
                }
            }
            else if(node==head){
                head=null;
                tail=null;
            }
            
        }
        
        public void insertNode(Node node){
            node.next = head;
            if(head!=null){
                head.prev = node;
            }
            
            head = node;
            if(tail == null){
                tail = node;
            }
        }
        
        // moves node to front of the queue.
        public void moveToFront(Node node){
            if(node!=null && node!=head){
                node.prev.next = node.next;
                if(node.next!=null){
                    node.next.prev = node.prev;
                }
                
            }
            insertNode(node);
        }
        
        /*************************************************************
         * the oldest node is always at the tail
         * when we remove the tail node, the oldest node is evicted.
         * 
         *************************************************************/
        public Node removeTail(){
            Node oldestNode = tail;
            if(tail!=null && tail==head){
                tail=null;
                head = null;
            }
            else if(tail!=null && tail!=head){
                tail.prev.next=null;
                tail = tail.prev;
            }
            
            return oldestNode;
        }
    }
    
    
}
