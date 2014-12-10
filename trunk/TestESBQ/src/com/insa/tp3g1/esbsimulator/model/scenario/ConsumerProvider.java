
package com.insa.tp3g1.esbsimulator.model.scenario;

import javax.xml.bind.annotation.XmlAttribute;


/**
 * Defines what is common between consumers and providers
 * @author Louis
 */
public abstract class ConsumerProvider {

	/**
	 * Id of the Consumer/Provider
	 */
	private int id;

	/**
	 * Port of the Consumer/Provider
	 */
    private int port;

	/**
	 * Protocol used by the Consumer/Provider
	 */
    private String protocol;

    //private Behavior behavior;

	/**
	 * Name of the Consumer/Provider
	 */
    private String name;

    public ConsumerProvider(){
    	
    }
    
	public ConsumerProvider(int id, int port, String protocol, /*Behavior behavior,*/ String name) {
		super();
		this.id = id;
		this.port = port;
		this.protocol = protocol;
		//this.behavior = behavior;
		this.name = name;
	}
	
	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/*public Behavior getBehavior() {
		return behavior;
	}

	//@XmlElementWrapper(name = "stateList")  
	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
