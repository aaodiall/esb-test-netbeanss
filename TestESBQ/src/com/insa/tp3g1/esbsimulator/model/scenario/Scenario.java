/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A scenario<br/>
 * To write a scenario in a file, just use the writeScenario method
 * @author Louis
 */
@XmlRootElement(name = "scenario")
@XmlType (propOrder={"name","numberConsumer","numberProvider","consumers","providers"})
public class Scenario {
	
	/**
	 * ID of the scenario
	 */
	private int id;
	
	/**
	 * Array of the providers
	 */
    private Provider[] providers;
    
    /**
     * Array of the consumers
     */
    private Consumer[] consumers;
    
    /**
     * Number of consumers
     */
    private int numberConsumer;
    
    /**
     * Number of providers
     */
    private int numberProvider;
    
    /**
     * Name of the scenario
     */
    private String name;
    

    public Scenario(int id, Provider[] providers, int numberConsumer,
			int numberProvider, String name, Consumer[] consumers) {
		super();
		this.id = id;
		this.providers = providers;
		this.numberConsumer = numberConsumer;
		this.numberProvider = numberProvider;
		this.name = name;
		this.consumers = consumers;
	}
    
    public Scenario(){
    	
    }

    /**
     * Print the scenario in XML
     */
	public void printXML(){
        try {
            JAXBContext context = JAXBContext.newInstance(Scenario.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to System.out for debugging
             m.marshal(this, System.out);
 

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Write the scenario in the appropriate file.<br/> Example of use : writeScenario("Scenario.xml");
	 * @param filepath path of the file. If no path is given, like "Scenario.xml", the file will be created in the local folder
	 * @throws FileNotFoundException 
	 */
	public void writeScenario(String filepath) throws FileNotFoundException{
        
        //ouvrir le fichier
        File file = new File(filepath);
            PrintStream printStream = new PrintStream(file);
            PrintStream console = System.out;
            
          //rediriger sortie standard pour utiliser la fonction printXML (modularite)
            System.setOut(printStream);
            this.printXML();
            
            //rediriger sortie standard normal (console)
            printStream.close();
            System.setOut(console);
    }

	@XmlAttribute
    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }


    public int getNumberConsumer ()
    {
        return numberConsumer;
    }

    public void setNumberConsumer (int numberConsumer)
    {
        this.numberConsumer = numberConsumer;
    }

    public int getNumberProvider ()
    {
        return numberProvider;
    }

    public void setNumberProvider (int numberProvider)
    {
        this.numberProvider = numberProvider;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
    
	
    public Provider[] getProviders() {
        return providers;
    }
    
    @XmlElementWrapper(name = "providers") 
    public void setProviders(Provider[] providers) {
        this.providers = providers;
    }

    @XmlElementWrapper(name = "consumers")
    public Consumer[] getConsumers() {
        return consumers;
    }

    public void setConsumers(Consumer[] consumers) {
        this.consumers = consumers;
    }

    
}
