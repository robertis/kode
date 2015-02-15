/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rtongbram
 */
public class PartitionService {
    
    
    /**********************************************************************************
     * Given an array of 2D points on a plane, find the nearest K points 
     * to the origin
     *
     * - use an array of size 2k , load 2k elements
     * - run selection algorithm to select k elements
     * - discard the k elements on the right
     * - load k elements from the input
     * - repeat the process until all elements in the input are processed
     * - return the last k elements 
     * 
     **********************************************************************************/
    public static List<Point> closestKPoints(List<Point> points, int k){
        List<Point> currList = new ArrayList<>();
        int currIndex = 0;
        //initialize by loading k points from the input
        for(int i = 0 ; i< k && i< points.size(); i++){
            currList.add(points.get(i));
            currIndex++;
        }
        //until input runs out, run selection algo on currList
        while(currIndex < points.size()){
            int limit = currIndex+k;
            for (int i = currIndex; i<limit && i < points.size(); i++) {
                currList.add(points.get(i));
                currIndex++;
            }
            currList = getMinKPoints(currList, k);
        }
        
        return currList;
    }
    
    /*************************************************************************
     * Run selection algorithm and return the smallest k elements.
     * Discard the right half and return the k elements.
     * 
     *************************************************************************/
    private static List<Point> getMinKPoints(List<Point> points, int k){
       // System.out.println("getMinKPoints");
        List<Point> result = new ArrayList<>();
        Random rand = new Random();
        if(points.size() <= k){
            return points;
        }
        int l = 0, r = points.size()-1;
        while(l <= r){
            if (l == r) {
                result = points.subList(0, k);
                return result;
            }
            int rIndex = rand.nextInt(r-l)+l;
            int p = partition(points, rIndex, l, r);
            if( k-1 == p){
                //discard the elements in the list from index p onwards and return the list
                result = points.subList(0, k);
                return result;
            }
            else if (k-1 < p){
                r=p-1;
            }
            else if (k-1 > p){
                l=p+1;
            }
        }
        
        return result;
    }
    /**********************************************************************
     * Partition the array on the randomIndex and return its index
     * Run QuickSelect :
     *  - move the pivot to the end of the array by swapping
     *  - iterate the rest of the elements
     *  - if the element is smaller than the pivot, keep it in the left
     *  - keep incrementing the storeIndex
     *  - after all elements are done, move the pivot to storeIndex
     * 
     ************************************************************************/
    private static int  partition(List<Point> points, int rIndex, int left, int right){
        int storeIndex = left;
        Point pivot = points.get(rIndex);
        // move the pivot to the end of the list
        swapPoints(points, rIndex,right);
        double pivotDistance = getDistanceFromOrigin(pivot);
        for(int i = left ; i < right ; i++){
            double distance = getDistanceFromOrigin(points.get(i));
            if(distance <= pivotDistance){
                swapPoints(points, i, storeIndex);
                storeIndex++;
            }
        }
        swapPoints(points,storeIndex,right);
        return storeIndex;
    }
    
    private static void swapPoints(List<Point> points, int x, int y){
        Point tmp = points.get(y);
        points.set(y, points.get(x));
        points.set(x,tmp);
        
    }
    private static double getDistanceFromOrigin(Point p){
        double distance = Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2));
        return distance;
    }
    
    public static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString(){
            String data = "{"+this.x+","+this.y+"}";
            return data;
        }
    }
}
