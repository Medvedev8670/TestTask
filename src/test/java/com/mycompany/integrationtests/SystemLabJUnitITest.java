/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.integrationtests;

import com.mycompany.testtask.SystemLab;
import com.mycompany.testtask.TestObj;
import com.mycompany.testtask.TestSubj;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Vlad
 */
public class SystemLabJUnitITest {
    @Test
    public void integrationSystemLabTest(){
        TestObj to2 = SystemLab.getInstance().CreateObj(2);
        TestObj to3 = SystemLab.getInstance().CreateObj(3);
        Set<TestObj> tosS1 = new HashSet();
        tosS1.add(to2);
        tosS1.add(to3);
        TestSubj s1 = SystemLab.getInstance().CreateSubj(tosS1, false);
        assertEquals(tosS1, s1.getOwnObjSet());//s1(2,3)
        
        TestObj to4 = SystemLab.getInstance().CreateObj(4);
        TestObj to5 = SystemLab.getInstance().CreateObj(5);
        Set<TestObj> tosS2 = new HashSet();
        tosS2.add(to2);
        tosS2.add(to4);
        tosS2.add(to5);
        TestSubj s2 = SystemLab.getInstance().CreateSubj(tosS2, false);
        tosS2.remove(to2);
        assertEquals(tosS1, s1.getOwnObjSet());// s1(2,3)
        assertEquals(tosS2, s2.getOwnObjSet());// s2(4,5)
        
        Set<TestObj> tosS3 = new HashSet();
        tosS3.add(to2);
        TestSubj s3 = SystemLab.getInstance().CreateSubj(tosS3, false);
        tosS1.remove(to2);
        assertEquals(tosS1, s1.getOwnObjSet());//s1(3)
        assertEquals(tosS2, s2.getOwnObjSet());//s2(4,5)
        assertEquals(tosS3, s3.getOwnObjSet());//s3(2)
        
        SystemLab.getInstance().DeleteSubj(s3);
        tosS1.add(to2);
        assertEquals(tosS1, s1.getOwnObjSet());//s1(2,3)
        assertEquals(tosS2, s2.getOwnObjSet());//s2(4,5)
        
        Set<TestObj> tosS4 = new HashSet();
        tosS4.add(to2);
        tosS4.add(to3);
        TestSubj s4 = SystemLab.getInstance().CreateSubj(tosS4, true);
        assertEquals(tosS1, s1.getOwnObjSet());//s1(2,3)
        assertEquals(tosS2, s2.getOwnObjSet());//s2(4,5)
        assertEquals(true, s4.getOwnObjSet().isEmpty());//s4 is empty
        
        SystemLab.getInstance().getObjSet().clear();
        SystemLab.getInstance().getSubjList().clear();
    }
}
