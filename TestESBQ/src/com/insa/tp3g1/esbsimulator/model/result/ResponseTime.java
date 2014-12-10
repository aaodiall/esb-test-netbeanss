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
public class ResponseTime {
    private String timeUnit;

    private String maxResponseTime;

    private String minResponseTime;

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
}
