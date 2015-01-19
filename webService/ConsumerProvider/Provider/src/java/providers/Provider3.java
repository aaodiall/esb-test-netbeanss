/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package providers;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author alpha
 */
@WebService(serviceName = "Provider3")
public class Provider3 extends ProviderBoss {
     private static final Integer ID=3;
     private final ProviderBean bean=new ProviderBean(ID, 1, 1);
     
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
        Integer processTime = bean.getProcessingTime();
        try {
          sleep(processTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Provider1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return processTime+";> "+ s +" <";
    }

    /**
     * To test the provider
     *
     * @return the configuration
     */
    @WebMethod(operationName = "test")
    public String test() throws Exception {
        setBean(bean);
        return getConfig(ID);
    }
    
    
}
