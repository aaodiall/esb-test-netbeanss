/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator;

import com.insa.tp3g1.esbsimulator.model.scenario.Consumer;
import com.insa.tp3g1.esbsimulator.model.scenario.DataExchangeSize;
import com.insa.tp3g1.esbsimulator.model.scenario.ProcessingTime;
import com.insa.tp3g1.esbsimulator.model.scenario.Provider;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.presenter.BuilderHandler;
import com.insa.tp3g1.esbsimulator.presenter.ParserHandler;
import com.insa.tp3g1.esbsimulator.presenter.ValidatorHandler;
import com.insa.tp3g1.esbsimulator.test.TestXML;
import com.insa.tp3g1.esbsimulator.view.MessageHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

/**
 *
 * @author alpha
 */
public class ESBSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean validationOk = false;
            
           
		ProcessingTime processingTime = new ProcessingTime(10, "ms");
		DataExchangeSize data = new DataExchangeSize(4, "byte");
                DataExchangeSize data1 = new DataExchangeSize(3, "byte");

		ArrayList<Consumer> consumers = new ArrayList<Consumer>();
		consumers.add(new Consumer(1, 13));
		consumers.add(new Consumer(2, 10));
		
		ArrayList<Provider> providers = new ArrayList<Provider>();
		providers.add(new Provider(1, processingTime, data));
                providers.add(new Provider(2, processingTime, data1));

		//test build
		//File xmlFile = new File("ScenarBuild.xml");
		Scenario scenario;
            
        try {
            scenario = new Scenario(1, providers, 2, "scenario", consumers);
            MessageHandler.providerHandler(scenario);
           // MessageHandler.consumerHandler(scenario);
        } catch (Exception ex) {
            Logger.getLogger(ESBSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           
		
    }
    
}
