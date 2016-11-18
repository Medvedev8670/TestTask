/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testtask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author Vlad
 */
public class SystemLab{
    private static SystemLab uniqueInst;
    private Set<TestObj> objSet;               //объекты системы
    private ArrayList<TestSubj> subjList;      //субъекты системы
    
    private SystemLab(){
        objSet = new HashSet();
        subjList = new ArrayList();
    }
    
    public static synchronized SystemLab getInstance(){
        if (uniqueInst == null){
            uniqueInst = new SystemLab();
        }
        return uniqueInst;
    }
    
    public Set<TestObj> getObjSet(){
        return objSet;
    }
    
    public ArrayList<TestSubj> getSubjList(){
        return subjList;
    }
    
    public TestObj CreateObj(int objId){
        TestObj to = new TestObj(objId);
        objSet.add(to);
        return to;
    }
    
  //добавляет субъект в систему
    public TestSubj CreateSubj(Set<TestObj> subsetObj, boolean isLP){
        TestSubj newTestSub = new TestSubj(subsetObj, isLP);
        Iterator<TestObj> iter =  newTestSub.getSubsetObj().iterator();
        while(iter.hasNext()){                                                  //сначала добавляем не занятые другими субъектами объекты
            TestObj tObj = iter.next();
            for(int i=subjList.size()-1; i >= 0; ){
                TestSubj ts = subjList.get(i);
                if(ts.getOwnObjSet().contains(tObj)){
                    break;
                }else{
                    i--;                           
                }
                if(i < 0){
                    newTestSub.addToOwnObj(tObj);                               //среди текущих субъектов системы нет конкурентов за данный объект, отдаем его во владение вновь добавляемому
                }
            }
        }
        iter =  newTestSub.getSubsetObj().iterator();
        while(iter.hasNext()){                                                  //проход по всем возможным объектам субъекта
            TestObj tObj = iter.next();
            if(!subjList.isEmpty()){                                            //если это первый субъект системы, то он будет владеть всеми возможными для него объектами
                for(int i=subjList.size()-1; i >= 0; ){
                    TestSubj ts = subjList.get(i);
                    if(ts.getOwnObjSet().contains(tObj)){
                        if(ts.getOwnObjSet().size()-1 > newTestSub.getOwnObjSet().size() || (ts.getIsLowPriorFlag()== true && newTestSub.getIsLowPriorFlag() != true)){ //Если в системе субъект с большим количеством объектов,чем вновь добавляемый, то в случае конкуренции этих субъектов за объект он передастся второму.
                            if(newTestSub.getIsLowPriorFlag() != true || ts.getIsLowPriorFlag()== true){
                                ts.getOwnObjSet().remove(tObj);
                                newTestSub.addToOwnObj(tObj);
                                subSort();
                            }
                        }
                        break;
                    }else{
                        i--;                                                    //если субъекты не конкурируют за объект идем дальше
                    }
                }   
            }else{
                newTestSub.addToOwnObj(tObj);
            }
        }
        subjList.add(newTestSub);
        subSort();
        return newTestSub;
    }
    
    //удаляем объект из системы
    public void DeleteSubj(TestSubj TestSubForDel){
        TestSubj tsWithLP = null;
        Iterator<TestObj> iter =  TestSubForDel.getOwnObjSet().iterator();
        subjList.remove(TestSubForDel);
        subjList.trimToSize();
        while(iter.hasNext()){                                                  //распределяем освободившиеся объекты по другим субъектам
            TestObj tObj = iter.next();
            for(int i=0; i < subjList.size(); i++){
                TestSubj ts = subjList.get(i);         
                if(ts.getSubsetObj().contains(tObj)){
                    if(ts.getIsLowPriorFlag() != true){
                        ts.addToOwnObj(tObj);
                        subSort();
                        break;
                    }else{
                        if(tsWithLP == null)
                            tsWithLP = ts;
                    }
                }
                if(i == subjList.size()-1 && tsWithLP != null){
                    tsWithLP.addToOwnObj(tObj);
                    subSort();
                    tsWithLP = null;   
                }
            }
        }
    }
    
    
    //сортирует субъекты по количеству объектов во владении
    private void subSort(){
        subjList.sort(new Comparator<TestSubj>(){
            @Override
            public int compare(TestSubj o1, TestSubj o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
