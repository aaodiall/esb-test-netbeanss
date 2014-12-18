package com.insa.tp3g1.esbsimulator.test;

import java.io.*;
import java.util.ArrayList;

import com.insa.tp3g1.esbsimulator.model.scenario.Consumer;
import com.insa.tp3g1.esbsimulator.model.scenario.DataExchangeSize;
import com.insa.tp3g1.esbsimulator.model.scenario.ProcessingTime;
import com.insa.tp3g1.esbsimulator.model.scenario.Provider;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.presenter.BuilderHandler;
import com.insa.tp3g1.esbsimulator.presenter.ParserHandler;
import com.insa.tp3g1.esbsimulator.presenter.ValidatorHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

public class TestXML {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ProcessingTime processingTime = new ProcessingTime(15, "ms");
		DataExchangeSize data = new DataExchangeSize(10, "byte");

		ArrayList<Consumer> consumers = new ArrayList<Consumer>();
		consumers.add(new Consumer(1, 13));
		consumers.add(new Consumer(2, 0));
		
		ArrayList<Provider> providers = new ArrayList<Provider>();
		providers.add(new Provider(1, processingTime, data));

		//test build
		File xmlFile = new File("ScenarBuild.xml");
		Scenario scenario = new Scenario(1, providers, 2, "scenario", consumers);
        
            try {
                BuilderHandler.createXmlFileFromObject(xmlFile, scenario);
            } catch (FileNotFoundException ex) {
                System.out.print("Error: File not found");
                //ex.printStackTrace();
            } catch (JAXBException ex) {
                System.out.print("Error: JAXB exception - " + ex.getMessage());
               //ex.printStackTrace();
            } 
           	
		//test validate and parse
		File xsdFile = new File("scenario.xsd");
		boolean validationOk = false;
           
            try {
                validationOk = ValidatorHandler.isXmlValidAgainstXsd(xmlFile, xsdFile);
            } catch (SAXException ex) {
                System.out.println("Error: SAX exception - " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("Error: IO exception - " + ex.getMessage());
            }
            
		
            if(validationOk) {
                    System.out.println("Validation OK");
                try {
                    Scenario scCreated = (Scenario) ParserHandler.getInstanceFromXmlFile(xmlFile, Scenario.class);
                } catch (JAXBException ex) {
                    System.out.println("Error: JAXB exception - " + ex.getMessage());
                }
            }
            else {
                    System.out.println("Validation error");
            }
		
		
	}

}
