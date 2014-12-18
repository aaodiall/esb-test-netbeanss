/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

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
@XmlType (propOrder={"name","numberConsumerProvider","consumers","providers"})
public class Scenario {
	
	/**
	 * ID of the scenario
	 */
	private int id;
	
	/**
	 * Array of the providers
	 */
    private ArrayList<Provider> providers;
    
    /**
     * Array of the consumers
     */
    private ArrayList<Consumer> consumers;
    
    /**
     * Number of consumers & providers
     */
    private int numberConsumerProvider;

    
    /**
     * Name of the scenario
     */
    private String name;
    

    public Scenario(int id, ArrayList<Provider> providers, int numberConsumerProvider,
            String name, ArrayList<Consumer> consumers) {
		super();
		this.id = id;
		this.providers = providers;
		this.numberConsumerProvider = numberConsumerProvider;
		this.name = name;
		this.consumers = consumers;
	}
    
    public Scenario(){
    	
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


    public int getNumberConsumerProvider ()
    {
        return numberConsumerProvider;
    }

    public void setNumberConsumerProvider (int numberConsumerProvider)
    {
        this.numberConsumerProvider = numberConsumerProvider;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
    
	
    public ArrayList<Provider> getProviders() {
        return providers;
    }
    
    @XmlElementWrapper(name = "providers") 
    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    @XmlElementWrapper(name = "consumers")
    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    
}
