/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package providers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author alpha
 */
@WebService(serviceName = "Provider5")
public class Provider5 {
     private static final Integer ID=5;
     private ProviderBean bean=new ProviderBean(ID, 1, 1);
     
     /*creation du listenner
     / *Appelle config pour congigure le provider
     * */
     

    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello() {
        String s = bean.fillString('X', bean.getSizeDataExchange()); 
        try {
            Thread.sleep(bean.getProcessingTime());
        } catch (InterruptedException ex) {
            Logger.getLogger(Provider5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "> "+ s +" <";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listenner")
    public Boolean listenner(@WebParam(name = "dataExchange") Integer dataExchange, @WebParam(name = "processingTime") Integer processingTime) {
       // Pour tester
        
        this.bean.setProcessingTime(processingTime);
        this.bean.setSizeDataExchange(dataExchange);
       
        return true;
    }
    
    // TO DO getConfig
    
}
