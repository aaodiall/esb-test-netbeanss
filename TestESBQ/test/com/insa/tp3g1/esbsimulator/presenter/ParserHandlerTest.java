
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author belliot
 */
public class ParserHandlerTest {
    
    public ParserHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Method executed once, before all the tests
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Method executed once, after all the tests
    }
    
    @Before
    public void setUp() {
        // Method executed before each test
    }
    
    @After
    public void tearDown() {
        // Method executed after each test
    }
    
    /**
     * Test of getInstanceFromXmlFile method, of class ParserHandler.
     */
    @Test
    public void testGetInstanceFromXmlFile() throws Exception {
        System.out.println("Testing getInstanceFromXmlFile()");
        
        File xmlFile = new File("ScenarioTest.xml");
        Class<?> c = Scenario.class;
        
        Scenario result = (Scenario) ParserHandler.getInstanceFromXmlFile(xmlFile, Scenario.class);
        
        /********* Creation of the solution expected *********/
        int id = 1;
        int coupleConsProv = 3;
        
        // Consumers
        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
        consumers.add(new Consumer(1, 13));
        consumers.add(new Consumer(2, 10));
        consumers.add(new Consumer(3, 7));
        
        // Providers
        ArrayList<Provider> providers = new ArrayList<Provider>();
        
        DataExchangeSize data = new DataExchangeSize(20, "byte");
        ProcessingTime processingTime = new ProcessingTime(6, "ms");
        providers.add(new Provider(1, processingTime, data));
	
        data = new DataExchangeSize(21, "byte");
        processingTime = new ProcessingTime(9, "ms");
        providers.add(new Provider(2, processingTime, data));

        data = new DataExchangeSize(22, "byte");
        processingTime = new ProcessingTime(12, "ms");
        providers.add(new Provider(3, processingTime, data));
        
        // Scenario
        Scenario scenario = new Scenario(17, providers, coupleConsProv, "test1", consumers);
        result = new Scenario(17, providers, coupleConsProv, "test1", consumers);
        
        /*********************************************************/
        
        assertEquals(scenario, result);
    }
}