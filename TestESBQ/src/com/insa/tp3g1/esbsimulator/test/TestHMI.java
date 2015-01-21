/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.test;


import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.presenter.BuilderHandler;
import com.insa.tp3g1.esbsimulator.view.HMI;
import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author julie
 */
public class TestHMI {
    
    public static void main(String[] args) throws JAXBException {
    
        File xmlFile = new File("ScenarBuild.xml");
        HMI hmi = new HMI();
        Scenario scenario = hmi.createScenario();
        try {
            BuilderHandler.createXmlFileFromObject(xmlFile, scenario);
        } catch (FileNotFoundException ex) {
            System.out.print("Error: File not found");
//                ex.printStackTrace();
        } catch (JAXBException ex) {
            System.out.print("Error: JAXB exception - " + ex.getMessage());
//                ex.printStackTrace();
        } 
    
        
    }
            
    
}
