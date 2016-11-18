/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testtask;

/**
 *
 * @author Vlad
 * класс объекта
 */
public class TestObj {
    private final int objId;
    
    TestObj(int objId){
        this.objId = objId;
    }
    
    @Override
    public int hashCode(){
        int x = 31;
        return x*objId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestObj other = (TestObj) obj;
        if(objId != other.objId){
            return false;
        }
        return true;
    }
}
