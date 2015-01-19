package consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaki
 */
public class Invoker2 extends Thread {

    private static final String LOG_NAME = "logs";
    static Thread runna;

    @Override
    public void run() {
        try {
            emitLog("2;sent;" + Thread.currentThread().getId() + ";" + System.nanoTime());
            String res = hello();
            emitLog("2;recieved;"+ res + ";" + Thread.currentThread().getId() + ";" + System.nanoTime());
        } catch (IOException e) {
            try {
                emitLog("2;lost;" + Thread.currentThread().getId() + ";" + System.nanoTime());
            } catch (IOException ex) {
                Logger.getLogger(Invoker2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }

    private static String hello() {
        compositeprovider2.CompositeProvider2Service1 service = new compositeprovider2.CompositeProvider2Service1();
        compositeprovider2.Provider2 port = service.getCasaPort1();
        String str = port.hello();
        return port.hello();
    }
    
    public void emitLog(String msg) throws IOException {

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