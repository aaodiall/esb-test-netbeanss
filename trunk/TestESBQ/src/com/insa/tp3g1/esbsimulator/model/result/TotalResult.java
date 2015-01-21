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

@XmlType (propOrder={"responseTime", "averageResponseTime", "lostRequests"})
public class TotalResult {
    
    /**
     * Attributes
     */
    
    private String averageResponseTime;

    private String lostRequests;

    private ResponseTime responseTime;
    
    /**
     * Constructor
     * @param averageResponseTime
     * @param lostRequests
     * @param responseTime 
     */
    public TotalResult(String averageResponseTime, String lostRequests, ResponseTime responseTime) {
        this.setAverageResponseTime(averageResponseTime);
        this.setLostRequests(lostRequests);
        this.setResponseTime(responseTime);
    }
    
    public TotalResult(){
        
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

    public String getLostRequests ()
    {
        return lostRequests;
    }

    public void setLostRequests (String lostRequests)
    {
        this.lostRequests = lostRequests;
    }

    public ResponseTime getResponseTime ()
    {
        return responseTime;
    }

    public void setResponseTime (ResponseTime responseTime)
    {
        this.responseTime = responseTime;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TotalResult other = (TotalResult) obj;
        if ((this.averageResponseTime == null) ? (other.averageResponseTime != null) : !this.averageResponseTime.equals(other.averageResponseTime)) {
            return false;
        }
        if ((this.lostRequests == null) ? (other.lostRequests != null) : !this.lostRequests.equals(other.lostRequests)) {
            return false;
        }
        if (this.responseTime != other.responseTime && (this.responseTime == null || !this.responseTime.equals(other.responseTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TotalResult{" + "averageResponseTime=" + averageResponseTime + ", lostRequests=" + lostRequests + ", responseTime=" + responseTime + '}';
    }
    
    
    
}
