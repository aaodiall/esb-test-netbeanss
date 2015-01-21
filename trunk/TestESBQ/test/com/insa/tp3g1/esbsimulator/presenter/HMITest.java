/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.presenter;

import com.insa.tp3g1.esbsimulator.model.scenario.Consumer;
import com.insa.tp3g1.esbsimulator.model.scenario.DataExchangeSize;
import com.insa.tp3g1.esbsimulator.model.scenario.ProcessingTime;
import com.insa.tp3g1.esbsimulator.model.scenario.Provider;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.view.HMI;
import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author julie
 */
public class HMITest {
    
    public HMITest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

/**
     * Test of createXmlFileFromObject method, of class BuilderHandler.
     */
    @Test
    public void testCreateScenario() throws Exception {
        System.out.println("createSenario");
        
        File fileToBuild = new File("ScenarBuild.xml");
        File expectedOutput = new File("JUnitScenarBuild.xml");
        
        //create objects to create xml files
        HMI hmi = new HMI();
        Scenario scenario = hmi.createScenario();
        

        //build xml from objects
        BuilderHandler.createXmlFileFromObject(fileToBuild, scenario);
                
        //compare files
        boolean compare = FileUtils.contentEquals(fileToBuild, expectedOutput);
       
        System.err.println(fileToBuild.getAbsoluteFile()+" "+expectedOutput.getAbsoluteFile());
        
        if(!compare) {
            fail("XML output file is different from what is expected.");
        }
    }

}
