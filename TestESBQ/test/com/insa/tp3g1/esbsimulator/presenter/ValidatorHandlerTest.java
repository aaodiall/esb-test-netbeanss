/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.presenter;

import java.io.File;
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
public class ValidatorHandlerTest {
    
    public ValidatorHandlerTest() {
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
     * Test of isXmlValidAgainstXsd method, of class ValidatorHandler.
     */
    @Test
    public void testIsXmlValidAgainstXsd() throws Exception {
        System.out.println("Testing isXmlValidAgainstXsd()");

        File xmlFile = new File("ScenarioTest.xml");
        File xsdFile = new File("scenario.xsd");
        
        boolean expResult = true;
        boolean result = ValidatorHandler.isXmlValidAgainstXsd(xmlFile, xsdFile);
        
        assertEquals(expResult, result);
    }
}