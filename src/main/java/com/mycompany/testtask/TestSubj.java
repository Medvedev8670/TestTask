package com.mycompany.testtask;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vlad
 * класс субъекта
 */
public class TestSubj{
    private final Set<TestObj> subsetObj = new HashSet();;   //возможные объекты во владении
    private Set<TestObj> ownSetObj;                          //объекты во владении
    private boolean isLowPrior;
    
    TestSubj(final Set<TestObj> subsetObj, boolean isLP){  
        ownSetObj = new HashSet();
        this.subsetObj.addAll(subsetObj);
        isLowPrior = isLP;
    }
    
    
    public int compareTo(TestSubj ts){
         return this.ownSetObj.size() - ts.ownSetObj.size();
    }
    
    public Set<TestObj> getOwnObjSet(){
        return ownSetObj;
    }
    
    public Set<TestObj> getSubsetObj(){
        return subsetObj;
    }
    
    public boolean getIsLowPriorFlag(){
        return isLowPrior;
    }
    
 
    public void addToOwnObj(TestObj obj){
        ownSetObj.add(obj);
    }
}
