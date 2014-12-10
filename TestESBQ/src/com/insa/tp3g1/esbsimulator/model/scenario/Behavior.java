/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.scenario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@XmlAccessorType(XmlAccessType.FIELD)
public class Behavior {
	
	private int requestBySeconds;
	@XmlAttribute
	private String unitProcessingTime;
	private int processingTime;
    
	public Behavior(){
		
	}
	
	public Behavior(int requestBySeconds, String unitProcessingTime, int processingTime) {
		super();
		this.requestBySeconds = requestBySeconds;
		this.unitProcessingTime = unitProcessingTime;
		this.processingTime = processingTime;
	}

	public int getRequestBySeconds() {
		return requestBySeconds;
	}

	public void setRequestBySeconds(int requestBySeconds) {
		this.requestBySeconds = requestBySeconds;
	}

	public String getUnitProcessingTime() {
		return unitProcessingTime;
	}

	public void setUnitProcessingTime(String unitProcessingTime) {
		this.unitProcessingTime = unitProcessingTime;
	}

	public int getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}

}
