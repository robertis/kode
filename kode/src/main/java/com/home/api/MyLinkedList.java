/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

/**
 *
 * @author rtongbram
 */
public class MyLinkedList{
        
        private Node head;
        private Node tail;
        public MyLinkedList(){
            //head = new Node();
        }
        public MyLinkedList(Node node){
            head = node;
        }
        
        public Node getHead(){
            return head;
        }
        
        public void populatList(int...values){
            Node curr = head;
            for(int i : values){
                Node n = new Node(i);
                if(head == null){
                    head=n;
                    curr = n;
                }
                else{
                    curr.next = n;
                    curr = n;
                }
            }
        }
        
        public void addNode(Node n){
            if(head == null){
                head=n;
                tail=n;
            }
            else{
                tail.next = n;
                tail = tail.next;
            }
        }
        
        //public void reverset
        
        public void printList(){
            Node curr = head;
            while(curr!=null){
                System.out.print(curr.data+"=>");
                curr = curr.next;
            }
            System.out.println();
        }
        
        public class Node{
            int data;
            Node next;
            public Node(int value){
                this.data = value;
            }
        }
    }