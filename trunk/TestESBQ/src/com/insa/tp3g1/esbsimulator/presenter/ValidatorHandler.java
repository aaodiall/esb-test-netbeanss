/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.presenter;

import java.io.File;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
 *
 * @author Mike
 */
public class ValidatorHandler implements ErrorHandler  {

        /**
         * Validate an XML file against an XSD file
         * @param xmlFile
         * @param xsdFile
         * @return 
         */
	public static boolean isXmlValidAgainstXsd(File xmlFile, File xsdFile) {
		boolean validationOk = true;

		try {
			String schemaLang = "http://www.w3.org/2001/XMLSchema";

			SchemaFactory factory = SchemaFactory.newInstance(schemaLang);

			Schema schema = factory.newSchema(new StreamSource(xsdFile));
			Validator validator = schema.newValidator();

			validator.validate(new StreamSource(xmlFile));

		} catch (SAXException e) {
			System.out.println("SAX exception :" + e.getMessage());
			validationOk = false;
		} catch (Exception ex) {
			System.out.println("Exception :" + ex.getMessage());
			validationOk = false;
		}

		return validationOk;
	}

}
