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
public class LinkConsumerProvider {
    private String averageResponseTime;

    private String providerId;

    private String consumerId;

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
}
