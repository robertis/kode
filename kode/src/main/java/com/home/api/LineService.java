/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author rtongbram
 */
class Point {

    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Line {

    double epsilon = 0.000001;
    double slope;
    double intercept;
    private boolean infiniteSlope = false;

    Line(Point p1, Point p2) {
        if (Math.abs(p2.x - p1.x) < this.epsilon) {
            // if the line is vertical , the infinite slope is true.
            this.infiniteSlope = true;
        } else {
            // Use both the 2 points to find slope
            this.slope = (p2.y - p1.y) / (p2.x - p1.x);
            //y = mx+b
            // b = y-mx;
            //Once you get slope, you can use it to find the intercept 
            // using either p1 or p2.
            this.intercept = p1.y - this.slope * p1.x;
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Line) {
            Line otherLine = (Line) other;
            if (otherLine.intercept == this.intercept 
                    && otherLine.slope == this.slope &&
                    otherLine.infiniteSlope == this.infiniteSlope) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.slope) ^ (Double.doubleToLongBits(this.slope) >>> 32));
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.intercept) ^ (Double.doubleToLongBits(this.intercept) >>> 32));
        return hash;
    }

    public String toString() {
        return " slope = " + this.slope + "\nintercept = " + this.intercept;
    }
}
/*
 * (2,1) and (3,2)
 *  slope = 1/1 = 1
 *  intr1 = 1-2 = -1
 * intr2 = 2 - 3 = -1
 * 
 */
public class LineService {

    /*
     * Iterate the points in 2 for loops ( 2 loops cos we need 2 points to form a line)
     * for every 2 points, form a line and check if its already in the hash map
     * If already present increment the count , otherwise add it to the hashmap
     * 
     * After iterating all the points , check the line with the highest count
     * 
     */
    public static Line mostPopularLine(List<Point> points) {
        Line result = null;
        int len = points.size();

        Map<Line, Set<Point>> hm = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i != j) {
                    Line l = new Line(points.get(i), points.get(j));
                    if (hm.containsKey(l)) {
                        Set<Point> values = hm.get(l);
                        values.add(points.get(i));
                        values.add(points.get(j));
                        hm.put(l, values);
                    } else {
                        Set<Point> values = new HashSet<>();
                        values.add(points.get(i));
                        values.add(points.get(j));
                        hm.put(l, values);
                    }
                    //hm.put(l, null)
                }
            }
        }

        int maxCount = 0;
        Set<Map.Entry<Line, Set<Point>>> entries = hm.entrySet();
        for (Map.Entry<Line, Set<Point>> entry : entries) {
            Line l = entry.getKey();
            Set<Point> vals = entry.getValue();
            if (vals.size() > maxCount) {
                maxCount = vals.size();
                result = l;
            }
        }
        return result;
    }
}
