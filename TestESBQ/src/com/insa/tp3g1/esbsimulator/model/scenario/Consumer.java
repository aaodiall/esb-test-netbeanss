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
import javax.xml.bind.annotation.XmlRootElement;


/**
 * A consumer<br/>
 * Extends ConsumerProvider
 * @author Louis
 */

@XmlRootElement(name = "consumer")
@XmlAccessorType (XmlAccessType.FIELD)
public class Consumer extends ConsumerProvider{

	/**
	 * ID of the producer
	 */
	private int producerId;
	/**
	 * Request by seconds send by the producer
	 */
	private int requestBySeconde;

	public Consumer(){

	}

	public Consumer(int id, int port, String protocol,/*Behavior behavior,*/ String name, int producerId, int requestBySeconde) {
		super(id, port, protocol,/* behavior,*/ name);
		this.producerId = producerId;
		this.requestBySeconde = requestBySeconde;
	}

	public int getRequestBySeconde() {
		return requestBySeconde;
	}

	public void setRequestBySeconde(int requestBySeconde) {
		this.requestBySeconde = requestBySeconde;
	}

	public int getProducerId ()
	{
		return producerId;
	}

	public void setProducerId (int producerId)
	{
		this.producerId = producerId;
	}

	/**
	 * Print the xml of the consumer to the standard output stream <br/>
	 * Mainly used for debugged purposes
	 */
	public void printXML() {
		try {
			JAXBContext context = JAXBContext.newInstance(Consumer.class);
			Marshaller m = context.createMarshaller();
			//for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
			m.marshal(this, System.out);


		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}		
}

