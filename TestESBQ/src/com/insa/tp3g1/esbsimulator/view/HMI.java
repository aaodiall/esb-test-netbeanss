/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.view;

import com.insa.tp3g1.esbsimulator.model.scenario.Consumer;
import com.insa.tp3g1.esbsimulator.model.scenario.DataExchangeSize;
import com.insa.tp3g1.esbsimulator.model.scenario.ProcessingTime;
import com.insa.tp3g1.esbsimulator.model.scenario.Provider;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.presenter.ParserHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;

/**
 *
 * @authors alpha, julie
 */
public class HMI {
    
    
    Scanner scanner;
    
    /**
     * Constructor
     */
    public HMI() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * open a window to select a file
     * @return 
     */
    public File selectFile(){
        
        JFileChooser dialogue = new JFileChooser(".");
        File fichier = null;
        
            if (dialogue.showOpenDialog(null) ==  JFileChooser.APPROVE_OPTION) {
                    fichier = dialogue.getSelectedFile();
                     System.out.println(fichier);

            }
       
        return fichier;
    }
  
    /**
     * cree un scenario a partir des infos entrees par l'utilisateur
     * @return 
     */
    public Scenario createScenario() throws JAXBException {
        
        File file = this.selectFile();
        Scenario scenario = new Scenario();
            
        try {
            if (file == null) {

                System.out.println("Id of the scenario : \n");
                int id = this.scanner.nextInt();

                 System.out.println("Name of the scenario : \n");
                 String sleep = this.scanner.nextLine();
                 String name = this.scanner.nextLine();

                System.out.println("Number of consumers & providers: \n");
                int nbConsumersProviders = this.scanner.nextInt();

                ArrayList<Consumer> consumers = new ArrayList<Consumer>();
                int i;
                for (i=0; i<nbConsumersProviders; i++) {
                    System.out.println("Id of the consumer : \n");
                    int idc = this.scanner.nextInt();
                    System.out.println("Number of requests/second : \n");
                    int nbRequest = this.scanner.nextInt();

                    consumers.add(new Consumer(idc, nbRequest));
                }


                ArrayList<Provider> providers = new ArrayList<Provider>();
                int j;
                for (j=0; j<nbConsumersProviders; j++) {
                    System.out.println("Id of the provider : \n");
                    int idp = this.scanner.nextInt();

                    System.out.println("Value of the processing time : \n");
                    int valeurProcessingTime = this.scanner.nextInt();
                    System.out.println("Unity of the processing time : \n");
                    sleep = this.scanner.nextLine();
                    String uniteProcessingTime = this.scanner.nextLine();
                    ProcessingTime p = new ProcessingTime(valeurProcessingTime, uniteProcessingTime);

                    System.out.println("Value of the data exchanged : \n");
                    int valeurDataExchangeSize = this.scanner.nextInt();
                    System.out.println("Unity of the data excanged : \n");
                    sleep = this.scanner.nextLine();
                    String uniteDataExchangeSize = this.scanner.nextLine();
                    DataExchangeSize data = new DataExchangeSize(valeurDataExchangeSize, uniteDataExchangeSize);

                    providers.add(new Provider(idp, p, data)); 

                }
                
                scenario = new Scenario(id, providers, nbConsumersProviders, name, consumers);
            }
            else {
                scenario =(Scenario) ParserHandler.getInstanceFromXmlFile(file, Scenario.class);
            }

        
        } catch (Exception ex) {
                Logger.getLogger(HMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return scenario;
    }

}
