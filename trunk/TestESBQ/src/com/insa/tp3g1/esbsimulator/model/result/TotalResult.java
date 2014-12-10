/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.result;

/**
 *
 * @author alpha
 */
public class TotalResult {
    private String AverageResponseTime;

    private String lostRequests;

    private ResponseTime responseTime;

    public String getAverageResponseTime ()
    {
        return AverageResponseTime;
    }

    public void setAverageResponseTime (String AverageResponseTime)
    {
        this.AverageResponseTime = AverageResponseTime;
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
}
