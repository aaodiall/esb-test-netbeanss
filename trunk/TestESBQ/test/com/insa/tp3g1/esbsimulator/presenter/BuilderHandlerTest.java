/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.presenter;

import com.insa.tp3g1.esbsimulator.model.scenario.Consumer;
import com.insa.tp3g1.esbsimulator.model.scenario.DataExchangeSize;
import com.insa.tp3g1.esbsimulator.model.scenario.ProcessingTime;
import com.insa.tp3g1.esbsimulator.model.scenario.Provider;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.omg.PortableInterceptor.SUCCESSFUL;

/**
 *
 * @author bailleul
 */
public class BuilderHandlerTest {
    
    public BuilderHandlerTest() {
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

    /**
     * Test of createXmlFileFromObject method, of class BuilderHandler.
     */
    @Test
    public void testCreateXmlFileFromObject() throws Exception {
        System.out.println("createXmlFileFromObject");
        
        File fileToBuild = new File("ScenarBuild.xml");
        File expectedOutput = new File("JUnitScenarBuild.xml");
        
        //create objects to create xml files
        ProcessingTime processingTime = new ProcessingTime(15, "ms");
        DataExchangeSize data = new DataExchangeSize(10, "byte");

        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
        consumers.add(new Consumer(1, 13));
        consumers.add(new Consumer(2, 0));

        ArrayList<Provider> providers = new ArrayList<Provider>();
        providers.add(new Provider(1, processingTime, data));
        providers.add(new Provider(2, processingTime, data));
        
        Scenario scenario = new Scenario(1, providers, 2, "scenario", consumers);

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