/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.scenario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A consumer<br/>
 * Extends ConsumerProvider
 * 
 * @author Louis
 */

@XmlRootElement(name = "consumer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Consumer {

	/**
	 * Id of the Consumer/Provider
	 */
	private int id;
        
        /**
	 * Request by seconds send by the producer
	 */
	private int requestBySeconde;

	public Consumer() {

	}

	public Consumer(int id, int requestBySeconde) {
		this.id = id;
                this.requestBySeconde = requestBySeconde;
	}

        
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
        
	public int getRequestBySeconde() {
		return requestBySeconde;
	}

	public void setRequestBySeconde(int requestBySeconde) {
		this.requestBySeconde = requestBySeconde;
	}
}
