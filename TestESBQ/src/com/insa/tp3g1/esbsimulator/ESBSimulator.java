/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.insa.tp3g1.esbsimulator;

import com.insa.tp3g1.esbsimulator.model.result.Result;
import com.insa.tp3g1.esbsimulator.model.scenario.Consumer;
import com.insa.tp3g1.esbsimulator.model.scenario.DataExchangeSize;
import com.insa.tp3g1.esbsimulator.model.scenario.ProcessingTime;
import com.insa.tp3g1.esbsimulator.model.scenario.Provider;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.presenter.BuilderHandler;
import com.insa.tp3g1.esbsimulator.presenter.ParserHandler;
import com.insa.tp3g1.esbsimulator.presenter.ResultHandler;
import com.insa.tp3g1.esbsimulator.presenter.ValidatorHandler;
import com.insa.tp3g1.esbsimulator.test.TestXML;
import com.insa.tp3g1.esbsimulator.view.HMI;
import com.insa.tp3g1.esbsimulator.view.MessageHandler;
import static com.insa.tp3g1.esbsimulator.view.MessageHandler.sendConfig1;
import com.insa.tp3g1.esbsimulator.view.Observer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
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
    
    private static Result result;
    
    public static void setResult(Result result) {
        ESBSimulator.result = result;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JAXBException  {
        // TODO code application logic here
        boolean validationOk = false;
        
        
     /*   ProcessingTime processingTime = new ProcessingTime(10, "ms");
        DataExchangeSize data = new DataExchangeSize(4, "byte");
        DataExchangeSize data1 = new DataExchangeSize(3, "byte");
        
        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
        consumers.add(new Consumer(1, 100));
        consumers.add(new Consumer(2, 60));
        
        ArrayList<Provider> providers = new ArrayList<Provider>();
        providers.add(new Provider(1, processingTime, data));
        providers.add(new Provider(2, processingTime, data1));
        
        //test build
        //File xmlFile = new File("ScenarBuild.xml");
        Scenario scenario;*/
         
        Class<?> c = Scenario.class;
        
        File xmlFile = new File("ScenarBuild.xml");
        HMI hmi = new HMI();
        Scenario scenario = hmi.createScenario();
        
        try {
           // scenario = new Scenario(1, providers, 2, "scenario", consumers);
            //scenario =(Scenario) ParserHandler.getInstanceFromXmlFile(xmlFile, Scenario.class);
            BuilderHandler.createXmlFileFromObject(xmlFile, scenario);
            MessageHandler.providerHandler(scenario);
            /*new Thread(){
            @Override
            public void run(){
            try {
            MessageHandler.logGetter();
            } catch (Exception ex) {
            Logger.getLogger(ESBSimulator.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            }.start();*/
            //MessageHandler.logGetter();
            //Observer.observerLogGetter();
            Thread a=new Thread(new MessageHandler());
            a.start();
            
            System.out.println("main thread dort");
            sleep(500);
            MessageHandler.consumerHandler(scenario);
            sleep(40000);
            
            a.interrupt();
            
            
            System.out.println("main kill a");
            System.out.println("main kill a");
            System.out.println("main kill a");
            System.out.println("main kill a");
        } catch (Exception ex) {
            Logger.getLogger(ESBSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
