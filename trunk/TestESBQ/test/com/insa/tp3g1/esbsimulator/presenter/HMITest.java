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
import java.util.ArrayList;
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
        System.out.println("Test createSenario: ");
       
        //create scenario to test
        HMI hmi = new HMI();
        Scenario result = hmi.createScenario();
        
    
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
        
        /*********************************************************/
        
        assertEquals(scenario, result);
        
    }

}
