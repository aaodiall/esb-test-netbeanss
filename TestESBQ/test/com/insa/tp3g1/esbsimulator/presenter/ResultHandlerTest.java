/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.presenter;

import com.insa.tp3g1.esbsimulator.model.result.LinkConsumerProvider;
import com.insa.tp3g1.esbsimulator.model.result.ResponseTime;
import com.insa.tp3g1.esbsimulator.model.result.Result;
import com.insa.tp3g1.esbsimulator.model.result.TotalResult;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
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
public class ResultHandlerTest {
    
    public ResultHandlerTest() {
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
     * Test of createResultFile method, of class ResultHandler.
     */
    @Test
    public void testCreateResultFile() {
        try {
            // Re edit of the test written by Oussama, on the JUnit format now
            System.out.println("Testing createResultFile()");
            
            String filepath = "CreatedResult.xml";
            
            /****** Creating the expected result ******/
            ResponseTime responseTime = new ResponseTime("sec", "10", "6");
            TotalResult totalResult = new TotalResult("8", "10", responseTime);
            
            LinkConsumerProvider lcp1 = new LinkConsumerProvider("10", "1", "2");
            LinkConsumerProvider lcp2 = new LinkConsumerProvider("20", "3", "4");
            LinkConsumerProvider linkConsProv[] = {lcp1, lcp2};
            
            Result result = new Result(totalResult, linkConsProv);
            ResultHandler instance = new ResultHandler(result);
            /******************************************/
            
            instance.createResultFile(filepath);
            
            File fileResult = new File("CreatedResult.xml");
            File expectedFileResult = new File("ExpectedResult.xml");
            boolean comparison = FileUtils.contentEquals(fileResult, expectedFileResult);
            
            System.out.println("++++++++++++\n"+comparison+"\n++++++++++++");
            
            assertEquals(comparison, true);
        } catch (IOException ex) {
            Logger.getLogger(ResultHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}