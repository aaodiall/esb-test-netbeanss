/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.presenter;

import java.io.FileNotFoundException;

import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;

/**
 * Class used to build a XML Scenario
 * To write a scenario in a file, just use the createScenarioFile method
 * @author Louis
 */
public class BuilderHandler  implements ErrorHandler {
	/**
	 * A scenario
	 */
    private Scenario scenario;
    
    public BuilderHandler(Scenario scenario) {
		super();
		this.scenario = scenario;
	}

	/**
	 * Write the scenario in the appropriate file.<br/> Example of use : createScenarioFile("Scenario.xml");
	 * @param filepath path of the file. If no path is given, like "Scenario.xml", the file will be created in the local folder
	 */
	public void createScenarioFile(String filepath){
    	try {
			scenario.writeScenario(filepath);
		} catch (FileNotFoundException e) {
			fileNotFoundException(e);
		}
    }
	
	/**
	 * When a file is not found
	 */
	private void fileNotFoundException(FileNotFoundException e){
		System.err.println("BuilderHandler Error : File not found");
		e.printStackTrace();
	}
	
}
