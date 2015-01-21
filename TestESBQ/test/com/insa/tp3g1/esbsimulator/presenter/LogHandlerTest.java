/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.presenter;

import com.insa.tp3g1.esbsimulator.model.result.LinkConsumerProvider;
import com.insa.tp3g1.esbsimulator.model.result.ResponseTime;
import com.insa.tp3g1.esbsimulator.model.result.Result;
import com.insa.tp3g1.esbsimulator.model.result.TotalResult;
import com.insa.tp3g1.esbsimulator.model.result.logHelper;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import java.util.HashMap;
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
public class LogHandlerTest {
    
    public LogHandlerTest() {
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
     * Test of getTheLog method, of class LogHandler.
     */
//    @Test
//    public void testGetTheLog() {
//        System.out.println("getTheLog");
//        LogHandler instance = new LogHandler();
//        HashMap expResult = null;
//        HashMap result = instance.getTheLog();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of add method, of class LogHandler.
     */
//    @Test
//    public void testAdd() {
//        System.out.println("Testing add");
//        String log = "";
//        LogHandler instance = new LogHandler();
//        instance.add(log);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of fillInResultForm method, of class LogHandler.
     */
    @Test
    public void testFillInResultForm() {
        System.out.println("Testing fillInResultForm");
        
        /***** Creation of the result *****/
        
        /*** Hashmap creation ***/
        // Create and fill in the main hashmap 
        HashMap<String, HashMap<String, logHelper>> theLog = new HashMap<String, HashMap<String, logHelper>>();
        
        HashMap<String, logHelper> lineOfLog;
        logHelper littleHelper;
        
        // Create and fill in the log hashmap 1 for link 1: fail
//        lineOfLog = new HashMap<String, logHelper>();
//        lineOfLog.put("1", new logHelper(-1));
//        theLog.put("1", lineOfLog);
        
        // Create and fill in the log hashmap 2 for link 1
        lineOfLog =  new HashMap<String, logHelper>();
        
        theLog.put("1", new HashMap<String, logHelper>());
        lineOfLog = theLog.get("1");       
        
        lineOfLog.put("1", new logHelper());
        littleHelper = lineOfLog.get("1");
        
//        littleHelper = new logHelper(2);
        
        littleHelper.setProcessingTime(1);
        littleHelper.setSentTime(20*1000000);
        littleHelper.setRecievedTime(23*1000000);
//        lineOfLog.put("2", littleHelper);

//        
//        // Create and fill in the log hashmap 1 for link 2
//        lineOfLog =  new HashMap<String, logHelper>();
//        littleHelper = new logHelper(1);
//        littleHelper.setSentTime(10);
//        littleHelper.setRecievedTime(12);
//        lineOfLog.put("1", littleHelper);
//        theLog.put("2", lineOfLog);
//
        // Create and fill in the log hashmap 2 for link 2: fail
//        lineOfLog =  new HashMap<String, logHelper>();
//        lineOfLog.put("1", new logHelper(-1));
//        theLog.put("2", lineOfLog);
        
        // Create and fill in the log hashmap 3 for link 2
        lineOfLog =  new HashMap<String, logHelper>();
        littleHelper = new logHelper();
        littleHelper.setProcessingTime(3);
        littleHelper.setSentTime(30*1000000);
        littleHelper.setRecievedTime(34*1000000);
        lineOfLog.put("3", littleHelper);
        theLog.put("24", lineOfLog);
        
        /*** Creation of the expected scenario ***/
        ResponseTime responseTime = new ResponseTime("s", "1", "1");
        TotalResult totalResult = new TotalResult("1", "2", responseTime);

        LinkConsumerProvider lcp1 = new LinkConsumerProvider("1", "1", "1");
        LinkConsumerProvider lcp2 = new LinkConsumerProvider("1", "2", "2");
        LinkConsumerProvider linkConsProv[] = {lcp1, lcp2};

        Result expResult = new Result(totalResult, linkConsProv);
        
        
        /***** Results *****/
        
        LogHandler instance = new LogHandler();
        Result result = instance.fillInResultForm(theLog);
        
        /* Print results */
        System.out.println("++++++++++++++++++++++ expected");
        System.out.println(expResult.toString());
        System.out.println("++++++++++++++++++++++ created");
        System.out.println(result.toString());
        System.out.println("++++++++++++++++++++++ hashmap");
        System.out.println(theLog.toString());
        System.out.println("++++++++++++++++++++++");
        
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class LogHandler.
     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        LogHandler instance = new LogHandler();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
}