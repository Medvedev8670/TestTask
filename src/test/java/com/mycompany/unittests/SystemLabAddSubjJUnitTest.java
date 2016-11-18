/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unittests;

import com.mycompany.testtask.SystemLab;
import com.mycompany.testtask.TestSubj;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;



/**
 *
 * @author Vlad
 */
public class SystemLabAddSubjJUnitTest{
    
    @Before
    public void setUpAddSubj(){
        SystemLab.getInstance().CreateObj(1);
        SystemLab.getInstance().CreateObj(2);
    }
    
    @Test
    public void TestAddSubj(){
        TestSubj ts1 = SystemLab.getInstance().CreateSubj(SystemLab.getInstance().getObjSet(), false);
        assertEquals(SystemLab.getInstance().getObjSet(), ts1.getOwnObjSet());
    }
    
    @After
    public void tearDownAddSubj(){
        SystemLab.getInstance().getSubjList().clear();
        SystemLab.getInstance().getObjSet().clear();
    }
    
}
