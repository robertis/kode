/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;


import com.home.api.MyLinkedList.Node;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author rtongbram
 */
public class ListService {
    
    public static void main(String args[]){
        testSortedMerge();
    }
    private static void testList(){
        int[] arr = {6,1,2,3,4,5};
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.populatList(arr);
        linkedList.printList();
        linkedList=reverseList(linkedList);
        linkedList.printList();
    }
    
    
    private static void testSortedMerge(){
        int[] arr = {1,4,19};
        int[] arr2 = {2,20,39};
        int[] arr3 = {5,7,9};
        int[] arr4 = {6,10,15};
        MyLinkedList list1 = new MyLinkedList();
        MyLinkedList list2 = new MyLinkedList();
        MyLinkedList list3 = new MyLinkedList();
        MyLinkedList list4 = new MyLinkedList();
        list1.populatList(arr);
        list2.populatList(arr2);
        list3.populatList(arr3);
        list4.populatList(arr4);
        List<Node> nodes = new ArrayList<>();
        nodes.add(list1.getHead());
        nodes.add(list2.getHead());
        nodes.add(list3.getHead());
        nodes.add(list4.getHead());
        MyLinkedList result = sortedMerge(nodes);
        result.printList();
    }
    
    public static MyLinkedList reverseList(MyLinkedList list){
        
        Node resultNode=null;
        Node head = list.getHead();
        Node curr = head;
        while(curr!=null){
            Node tmp = curr.next;
            curr.next = resultNode;
            resultNode = curr;
            curr = tmp;
        }
        
        MyLinkedList result = new MyLinkedList(resultNode);
        return result;
    }
    
    
    public static MyLinkedList sortedMerge(List<Node> lists){
        
        MyLinkedList result = new MyLinkedList();
        Queue<Node> pQueue = new PriorityQueue<>(lists.size(), new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return n1.data-n2.data;
            }
        });
        
        for(Node n : lists){
            pQueue.add(n);
        }
        while(!pQueue.isEmpty()){
            //System.out.println("priority queue size ="+pQueue.size());
            Node n = pQueue.remove();
            result.addNode(n);
            if(n.next!=null){
                pQueue.add(n.next);
            }
            
        }
        return result;
    }
    
}
