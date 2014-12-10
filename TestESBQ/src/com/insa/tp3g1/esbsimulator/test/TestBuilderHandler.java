package com.insa.tp3g1.esbsimulator.test;

import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;

import com.insa.tp3g1.esbsimulator.model.scenario.Behavior;
import com.insa.tp3g1.esbsimulator.model.scenario.Consumer;
import com.insa.tp3g1.esbsimulator.model.scenario.DataExchangeSize;
import com.insa.tp3g1.esbsimulator.model.scenario.ProcessingTime;
import com.insa.tp3g1.esbsimulator.model.scenario.Provider;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.presenter.BuilderHandler;

public class TestBuilderHandler {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
            //Behavior b = new Behavior(2, "ms", 4);
		ProcessingTime p = new ProcessingTime(15, "ms");
			DataExchangeSize data = new DataExchangeSize(10, "byte");
		     Consumer c = new Consumer(57, 92, "SOAP" , "COCO", 13, 0);
		     //c.printXML();
		    Provider pro = new Provider(28, 92, "SOAP", p, "CICI", data);
		     //pro.printXML();
		    
			Consumer[] tabcons = new Consumer[2];
			tabcons[0]=c;
			tabcons[1]=c;
			
			Provider[] tabprov = new Provider[2];
			tabprov[0]=pro;
			tabprov[1]=pro;
			
			Scenario sc = new Scenario(1, tabprov, 2, 2, "scenario", tabcons);
			
			//sc.writeScenario("Scenario.xml");
			//sc.printXML();
			c.printXML();
			
			BuilderHandler build = new BuilderHandler(sc);
			build.createScenarioFile("ScenarBuild.xml");
			c.printXML();
                        


	}

}
