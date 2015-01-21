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
    @Test
    public void testAdd() {
        System.out.println("Testing add");
        
        /********* Create the expected result *********/
        /*** Hashmap creation ***/
        
        // Create and fill in the log hashmap 2 for link 1
        HashMap<String, HashMap<String, logHelper>> expLog = new HashMap<String, HashMap<String, logHelper>>();
        HashMap<String, logHelper> lineOfLog = new HashMap<String, logHelper>();
        logHelper littleHelper = new logHelper();
        littleHelper.setProcessingTime(2);
        littleHelper.setSentTime(20*1000000);
        littleHelper.setRecievedTime(23*1000000);
        lineOfLog.put("1", littleHelper);
        expLog.put("1", lineOfLog);
        
        /***************************************/
        
        String log = "1;sent;-1;Hello;1;20000000";
        LogHandler instance = new LogHandler();
        instance.add(log);
        log = "1;recieved;2;Bye;1;23000000";
        instance.add(log);
        HashMap<String, HashMap<String, logHelper>> createdLog = instance.getTheLog();
       
        System.out.println("++++++++++++++++++++++ expected hashmap");
        System.out.println(expLog.toString());
        System.out.println("++++++++++++++++++++++ created hashmap");
        System.out.println(createdLog.toString());
        System.out.println("++++++++++++++++++++++");
        
        //assertEquals(true,createdLog.equals(expLog));
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

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
        
        
        // Create and fill in the log hashmap 1 for link 2
        HashMap<String, logHelper> lineOfLog2 =  new HashMap<String, logHelper>();
        logHelper littleHelper21 = new logHelper(1);
        littleHelper21.setSentTime(10);
        littleHelper21.setRecievedTime(12);
        lineOfLog2.put("1", littleHelper21);

        // Create and fill in the log hashmap 2 for link 2: fail
        lineOfLog2 =  new HashMap<String, logHelper>();
        lineOfLog2.put("2", new logHelper(-1));
        
        // Create and fill in the log hashmap 3 for link 2
        logHelper littleHelper22 = new logHelper();
        littleHelper22.setProcessingTime(3);
        littleHelper22.setSentTime(30*1000000);
        littleHelper22.setRecievedTime(34*1000000);
        lineOfLog2.put("3", littleHelper22);
        theLog.put("2", lineOfLog2);
             
        
        // Create and fill in the log hashmap 1 for link 1: fail
        HashMap<String, logHelper> lineOfLog1 = new HashMap<String, logHelper>();
        lineOfLog1.put("1", new logHelper(-1));
//        theLog.put("1", lineOfLog1);
        
        // Create and fill in the log hashmap 2 for link 1
        logHelper littleHelper11 = new logHelper();
        littleHelper11.setProcessingTime(2);
        littleHelper11.setSentTime(20*1000000);
        littleHelper11.setRecievedTime(23*1000000);
        lineOfLog1.put("2", littleHelper11);
        theLog.put("1", lineOfLog1);

        
        /*** Creation of the expected scenario ***/
        ResponseTime responseTime = new ResponseTime("s", "1", "1");
        TotalResult totalResult = new TotalResult("1", "2", responseTime);

        LinkConsumerProvider lcp1 = new LinkConsumerProvider("1", "1", "1");
        LinkConsumerProvider lcp2 = new LinkConsumerProvider("1", "2", "2");
        LinkConsumerProvider linkConsProv[] = {lcp2, lcp1};

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
//        System.out.println(theLog.toString());
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