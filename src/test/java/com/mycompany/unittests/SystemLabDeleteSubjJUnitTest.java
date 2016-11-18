/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unittests;

import com.mycompany.testtask.SystemLab;
import com.mycompany.testtask.TestObj;
import com.mycompany.testtask.TestSubj;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Vlad
 */
public class SystemLabDeleteSubjJUnitTest {
       
    @Before
    public void setUpDeleteSubj(){
        TestObj to1 = SystemLab.getInstance().CreateObj(1);
        TestObj to2 = SystemLab.getInstance().CreateObj(2);
        TestObj to3 = SystemLab.getInstance().CreateObj(3);
        Set<TestObj> tos = new HashSet();
        tos.add(to1);
        tos.add(to2);
        tos.add(to3);
        TestSubj ts1 = SystemLab.getInstance().CreateSubj(tos, true);
        TestSubj ts2 = SystemLab.getInstance().CreateSubj(tos, false);
        tos.clear();
    }
    
    @Test
    public void TestDeleteSubj(){
        SystemLab.getInstance().DeleteSubj(SystemLab.getInstance().getSubjList().get(1));
        assertEquals(SystemLab.getInstance().getObjSet(), SystemLab.getInstance().getSubjList().get(0).getOwnObjSet());
    }
    
    @After
    public void tearDownDeleteSubj(){
        SystemLab.getInstance().getSubjList().clear();
        SystemLab.getInstance().getObjSet().clear();
    }
}
