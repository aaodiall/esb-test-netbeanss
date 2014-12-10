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

public class TestXML {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ProcessingTime p = new ProcessingTime(15, "ms");
		DataExchangeSize data = new DataExchangeSize(10, "byte");

		ArrayList<Consumer> consumers = new ArrayList<Consumer>();
		consumers.add(new Consumer(57, 92, "SOAP" , "COCO", 13, 0));
		consumers.add(new Consumer(82, 100, "SOAP" , "CACA", 12, 1));
		
		ArrayList<Provider> providers = new ArrayList<Provider>();
		providers.add(new Provider(28, 92, "SOAP", p, "CICI", data));

		//test build
		File xmlFile = new File("ScenarBuild.xml");
		Scenario scenario = new Scenario(1, providers, 2, 2, "scenario", consumers);

		BuilderHandler.createXmlFileFromObject(xmlFile, scenario);
		
		//test validate and parse
		File xsdFile = new File("scenario.xsd");
		boolean validationOk = ValidatorHandler.isXmlValidAgainstXsd(xmlFile, xsdFile);
		
		if(validationOk) {
			System.out.println("Validation OK");
			Scenario scCreated = (Scenario) ParserHandler.getInstanceFromXmlFile(xmlFile, Scenario.class);
		}
		else {
			System.out.println("Validation error");
		}
		
		
	}

}
