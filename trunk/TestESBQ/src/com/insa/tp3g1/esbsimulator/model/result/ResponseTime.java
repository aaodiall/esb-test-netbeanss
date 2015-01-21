/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.result;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @authors alpha, Julie
 */
public class ResponseTime {
    
    /**
     * Attributes
     */
    
    private String timeUnit;

    private String maxResponseTime;

    private String minResponseTime;
    
    /**
     * Constructor
     * @param timeUnit
     * @param maxResponseTime
     * @param minResponseTime 
     */
    public ResponseTime(String timeUnit, String maxResponseTime, String minResponseTime) {
        this.setTimeUnit(timeUnit);
        this.setMaxResponseTime(maxResponseTime);
        this.setMinResponseTime(minResponseTime);
    }
    
    public ResponseTime(){
        
    }

    /**
     * Getters and setters
     * @return 
     */
    
    @XmlAttribute
    public String getTimeUnit ()
    {
        return timeUnit;
    }

    public void setTimeUnit (String timeUnit)
    {
        this.timeUnit = timeUnit;
    }

    public String getMaxResponseTime ()
    {
        return maxResponseTime;
    }

    public void setMaxResponseTime (String maxResponseTime)
    {
        this.maxResponseTime = maxResponseTime;
    }

    public String getMinResponseTime ()
    {
        return minResponseTime;
    }

    public void setMinResponseTime (String minResponseTime)
    {
        this.minResponseTime = minResponseTime;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponseTime other = (ResponseTime) obj;
        if ((this.timeUnit == null) ? (other.timeUnit != null) : !this.timeUnit.equals(other.timeUnit)) {
            return false;
        }
        if ((this.maxResponseTime == null) ? (other.maxResponseTime != null) : !this.maxResponseTime.equals(other.maxResponseTime)) {
            return false;
        }
        if ((this.minResponseTime == null) ? (other.minResponseTime != null) : !this.minResponseTime.equals(other.minResponseTime)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "ResponseTime{" + "timeUnit=" + timeUnit + ", maxResponseTime=" + maxResponseTime + ", minResponseTime=" + minResponseTime + '}';
    }
    
    
    
}
