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
import java.util.ArrayList;
import java.util.Scanner;

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
     * cree un scenario a partir des infos entrees par l'utilisateur
     * @return 
     */
    public Scenario createScenario() {
        
        
        System.out.println("entrer l'id du scenario : \n");
        int id = this.scanner.nextInt();
        
         System.out.println("entrer le nom du scenario : \n");
         String sleep = this.scanner.nextLine();
         String name = this.scanner.nextLine();

        System.out.println("entrer le nombre de consumers: \n");
        int nbConsumers = this.scanner.nextInt();
        
        ArrayList<Consumer> consumers = new ArrayList<Consumer>();
        int i;
        for (i=0; i<nbConsumers; i++) {
            System.out.println("entrer l'id du consumer : \n");
            int idc = this.scanner.nextInt();
            System.out.println("entrer le port du consumer : \n");
            int port = this.scanner.nextInt();
            System.out.println("entrer le protocol du consumer : \n");
            sleep = this.scanner.nextLine();
            String protocol = this.scanner.nextLine();
            System.out.println("entrer le nom du consumer : \n");
            String nom = this.scanner.nextLine();
            System.out.println("entrer l'id du producer : \n");
            int idcp = this.scanner.nextInt();
            System.out.println("entrer le nombre de requetes par seconde : \n");
            int nbRequest = this.scanner.nextInt();
            
            consumers.add(new Consumer(idc, port, protocol , nom, idcp, nbRequest));
        }
        

        ArrayList<Provider> providers = new ArrayList<Provider>();
        int j;
        for (j=0; j<nbConsumers; j++) {
            System.out.println("entrer l'id du provider : \n");
            int idp = this.scanner.nextInt();
            System.out.println("entrer le port du provider : \n");
            int portp = this.scanner.nextInt();
            System.out.println("entrer le protocol du provider : \n");
            sleep = this.scanner.nextLine();
            String protocolp = this.scanner.nextLine();
            System.out.println("entrer le nom du provider : \n");
            String nomp = this.scanner.nextLine();
            
            System.out.println("entrer la valeur du processing time: \n");
            int valeurProcessingTime = this.scanner.nextInt();
            System.out.println("entrer l'unité du processing time: \n");
            sleep = this.scanner.nextLine();
            String uniteProcessingTime = this.scanner.nextLine();
            ProcessingTime p = new ProcessingTime(valeurProcessingTime, uniteProcessingTime);

            System.out.println("entrer la valeur du processing time: \n");
            int valeurDataExchangeSize = this.scanner.nextInt();
            System.out.println("entrer l'unité du processing time: \n");
            sleep = this.scanner.nextLine();
            String uniteDataExchangeSize = this.scanner.nextLine();
            DataExchangeSize data = new DataExchangeSize(valeurDataExchangeSize, uniteDataExchangeSize);
           
            providers.add(new Provider(idp, portp, protocolp, p, nomp, data)); 
            
        }

        Scenario scenario = new Scenario(id, providers, nbConsumers,nbConsumers, name, consumers);

        return(scenario);
    }

}
