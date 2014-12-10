/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.scenario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author alpha
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataExchangeSize {
    
    private int content;

    @XmlAttribute
    private String typeBehavior;
    
    public DataExchangeSize(){
    	
    }
    
    public DataExchangeSize(int content, String type) {
		super();
		this.content = content;
		this.typeBehavior = type;
	}

    public int getContent ()
    {
        return content;
    }

    public void setContent (int content)
    {
        this.content = content;
    }

   
    public String getType ()
    {
        return typeBehavior;
    }

	public void setType (String type)
    {
        this.typeBehavior = type;
    }
    
}
