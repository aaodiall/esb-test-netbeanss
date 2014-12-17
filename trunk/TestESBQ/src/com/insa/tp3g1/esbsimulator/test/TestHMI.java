/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.test;


import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.presenter.BuilderHandler;
import com.insa.tp3g1.esbsimulator.view.HMI;
import java.io.File;

/**
 *
 * @author julie
 */
public class TestHMI {
    
    public static void main(String[] args) {
    
        File xmlFile = new File("ScenarBuild.xml");
        HMI hmi = new HMI();
        Scenario scenario = hmi.createScenario();
        BuilderHandler.createXmlFileFromObject(xmlFile, scenario);
    
    }
    
}
