/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.api;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author rtongbram
 */
public class CatIterator<T> implements Iterator<T>{
    private Iterator<T> currentItr;
    private Iterator<Iterator<T>> allIters;
    public CatIterator(List<Iterator<T>> itersList){
        //Iterator<Iterator<T>> iter = itersList.iterator();
        allIters = itersList.iterator();
        currentItr = Collections.<T>emptyList().iterator();
        /*
        if(iter.hasNext()){
            currentItr = iter.next();
        }
        */
    }
    
    @Override
    public boolean hasNext() {
        
        while(!currentItr.hasNext() && allIters.hasNext()){
            currentItr = allIters.next();
        }
        /*
        if(currentItr.hasNext()) {
            return true;
        }
        else{
            while(allIters.hasNext()){
                currentItr = allIters.next();
                if(currentItr.hasNext()){
                    return true;
                }
            }
        }
        return false;
        */
        return currentItr.hasNext();
        
    }

    @Override
    public T next() {
        return currentItr.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
