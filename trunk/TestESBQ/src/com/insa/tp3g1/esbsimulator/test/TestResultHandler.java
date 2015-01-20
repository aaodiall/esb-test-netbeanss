/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.test;

import com.insa.tp3g1.esbsimulator.model.result.LinkConsumerProvider;
import com.insa.tp3g1.esbsimulator.model.result.ResponseTime;
import com.insa.tp3g1.esbsimulator.model.result.Result;
import com.insa.tp3g1.esbsimulator.model.result.TotalResult;
import com.insa.tp3g1.esbsimulator.presenter.ResultHandler;
import com.insa.tp3g1.esbsimulator.presenter.ResultHandler;

/**
 *
 * @author julie
 */
public class TestResultHandler {
    
    public static void main(String[] args) {
    
        ResponseTime rt = new ResponseTime("sec", "5", "6");
        TotalResult tr = new TotalResult("16", "10", rt);
        LinkConsumerProvider lcp1 = new LinkConsumerProvider("10", "1", "2");
        LinkConsumerProvider lcp2 = new LinkConsumerProvider("20", "3", "4");
        LinkConsumerProvider lcps[] = {lcp1, lcp2};
        Result r = new Result(tr, lcps);
        ResultHandler rh = new ResultHandler(r);
        rh.createResultFile("Result.xml");
        
    }
}
