/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.model.result;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

/**
 *
 * @authors alpha, Julie
 */

@XmlRootElement(name = "Result")
@XmlType (propOrder={"totalResult", "linksConsumerProvider"})
public class Result {
    
    /**
     * Attributes
     */
    
    /**
     * Total result
     */
    private TotalResult totalResult;

    /**
     * Array of consumers/providers links
     */
    private LinkConsumerProvider[] linksConsumerProvider;
    
    /**
     * Constructors
     * @param totalResult
     * @param linksConsumerProvider 
     */
    public Result( TotalResult totalResult, LinkConsumerProvider[] linksConsumerProvider){
        this.setTotalResult(totalResult);
        this.setLinksConsumerProvider(linksConsumerProvider);
    }
    
    public Result() {
    }

    /**
     * Getters and setters
     */
    
    public TotalResult getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(TotalResult totalResult) {
        this.totalResult = totalResult;
    }

    public LinkConsumerProvider[] getLinksConsumerProvider() {
        return linksConsumerProvider;
    }

    @XmlElementWrapper(name = "linksConsumerProvider")
    public void setLinksConsumerProvider(LinkConsumerProvider[] linksConsumerProvider) {
        this.linksConsumerProvider = linksConsumerProvider;
    }

    /**
     * methods
     */
    
    /**
     * Print the result in XML
     */
	public void printXML(){
        try {
            JAXBContext context = JAXBContext.newInstance(Result.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to System.out for debugging
             m.marshal(this, System.out);
 

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Write the results in a file
     * @param filepath
     * @throws FileNotFoundException 
     */
    public void writeResult(String filepath) throws FileNotFoundException{
        
        //open the file
        File file = new File(filepath);
        PrintStream printStream = new PrintStream(file);
        PrintStream console = System.out;

        //rediriger sortie standard pour utiliser la fonction printXML (modularite)
        System.setOut(printStream);
        this.printXML();

        //rediriger sortie standard normale (console)
        printStream.close();
        System.setOut(console);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Result other = (Result) obj;
        if (this.totalResult != other.totalResult && (this.totalResult == null || !this.totalResult.equals(other.totalResult))) {
            return false;
        }
        if (!Arrays.deepEquals(this.linksConsumerProvider, other.linksConsumerProvider)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "Result{" + "totalResult=" + totalResult + ", linksConsumerProvider=";
        for (int i = 0; i < linksConsumerProvider.length; i++){
            result += linksConsumerProvider[i].toString();
        }
        result += '}';
        return result;
    }
    
    
    
}
