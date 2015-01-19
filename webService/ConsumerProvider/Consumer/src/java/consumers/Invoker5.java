package consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaki
 */
public class Invoker5 extends Thread {
    
    private static final String LOG_NAME = "logs";
    static Thread runna;

    @Override
    public void run() {
        try {
            emitLog("5;sent;-1;x;" + Thread.currentThread().getId() + ";" + System.nanoTime());
            String res = hello();
            emitLog("5;recieved;"+ res + ";" + Thread.currentThread().getId() + ";" + System.nanoTime());
        } catch (IOException e) {
            try {
                emitLog("5;lost;" + Thread.currentThread().getId() + ";" + System.nanoTime());
            } catch (IOException ex) {
                Logger.getLogger(Invoker5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }

    private static String hello() {
        compositeprovider5.CompositeProvider5Service1 service = new compositeprovider5.CompositeProvider5Service1();
        compositeprovider5.Provider5 port = service.getCasaPort1();
        return port.hello();
    }
    
    public void emitLog(String msg) throws IOException
                 {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);
        
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(LOG_NAME, "fanout");

        String message = msg;

        channel.basicPublish(LOG_NAME, "", null, message.getBytes());
        //System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
    
}