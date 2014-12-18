/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.scenario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * A provider<br/>
 * Extends ConsumerProvider
 * @author Louis
 */
@XmlRootElement(name = "provider")
@XmlAccessorType (XmlAccessType.FIELD)
public class Provider {

        /**
	 * Id of the Consumer/Provider
	 */
	private int id;
    
	/**
	 * Data that is exchanged
	 */
	private DataExchangeSize dataExchangeSize;
	
	/**
	 * The processing time, i.e. a simulation of the web service running. Implemented here by a sleep
	 */
	private ProcessingTime processingTime;
	
	
	public Provider(){
		
	}
	
	public Provider(int id, ProcessingTime processingTime, DataExchangeSize dataExchangeSize ) {
		this.id = id;
		this.dataExchangeSize = dataExchangeSize;
		this.processingTime = processingTime;
	}
	
        
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DataExchangeSize getDataExchangeSize ()
	{
		return dataExchangeSize;
	}

	public void setDataExchangeSize (DataExchangeSize dataExchangeSize)
	{
		this.dataExchangeSize = dataExchangeSize;
	}
	
	public ProcessingTime getProcessingTime() {
		return processingTime;
	}
	
	
	public void setProcessingTime(ProcessingTime processingTime) {
		this.processingTime = processingTime;
	}

}
