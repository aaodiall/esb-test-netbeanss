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
public class ProcessingTime {
	
    private int content;
	@XmlAttribute
    private String timeUnit;
    
    public ProcessingTime(int content, String timeUnit) {
		super();
		this.content = content;
		this.timeUnit = timeUnit;
	}
    
    public ProcessingTime(){
    	
    }

    public int getContent ()
    {
        return content;
    }

    public void setContent (int content)
    {
        this.content = content;
    }

    public String getTimeUnit ()
    {
        return timeUnit;
    }

    public void setTimeUnit (String timeUnit)
    {
        this.timeUnit = timeUnit;
    }
    
}
