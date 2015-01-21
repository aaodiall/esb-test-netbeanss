/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.result;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @authors alpha, Julie
 */

@XmlType (propOrder={"consumerId", "providerId", "averageResponseTime"})
public class LinkConsumerProvider {
    
    /**
     * Attributes
     */
    
    private String averageResponseTime;

    private String providerId;

    private String consumerId;

    /**
     * Constructor
     * @param averageResponseTime
     * @param providerId
     * @param consumerId 
     */
    public LinkConsumerProvider(String averageResponseTime, String consumerId, String providerId) {
        this.setAverageResponseTime(averageResponseTime);
        this.setConsumerId(consumerId);
        this.setProviderId(providerId);
    }

    public LinkConsumerProvider(){
        
    }
    
    /**
     * Getters and setters
     * @return 
     */
    
    public String getAverageResponseTime ()
    {
        return averageResponseTime;
    }

    public void setAverageResponseTime (String averageResponseTime)
    {
        this.averageResponseTime = averageResponseTime;
    }

    public String getProviderId ()
    {
        return providerId;
    }

    public void setProviderId (String providerId)
    {
        this.providerId = providerId;
    }

    public String getConsumerId ()
    {
        return consumerId;
    }

    public void setConsumerId (String consumerId)
    {
        this.consumerId = consumerId;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LinkConsumerProvider other = (LinkConsumerProvider) obj;
        if ((this.averageResponseTime == null) ? (other.averageResponseTime != null) : !this.averageResponseTime.equals(other.averageResponseTime)) {
            return false;
        }
        if ((this.providerId == null) ? (other.providerId != null) : !this.providerId.equals(other.providerId)) {
            return false;
        }
        if ((this.consumerId == null) ? (other.consumerId != null) : !this.consumerId.equals(other.consumerId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LinkConsumerProvider{" + "averageResponseTime=" + averageResponseTime + ", providerId=" + providerId + ", consumerId=" + consumerId + '}';
    }
    
    
}
