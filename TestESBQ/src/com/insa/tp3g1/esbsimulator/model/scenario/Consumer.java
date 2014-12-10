/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.scenario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A consumer<br/>
 * Extends ConsumerProvider
 * 
 * @author Louis
 */

@XmlRootElement(name = "consumer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Consumer extends ConsumerProvider {

	/**
	 * Request by seconds send by the producer
	 */
	private int requestBySeconde;

	public Consumer() {

	}

	public Consumer(int id, int port, String protocol, String name,
			int producerId, int requestBySeconde) {
		super(id, port, protocol, name);
		this.requestBySeconde = requestBySeconde;
	}

	public int getRequestBySeconde() {
		return requestBySeconde;
	}

	public void setRequestBySeconde(int requestBySeconde) {
		this.requestBySeconde = requestBySeconde;
	}
}
