/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package providers;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author alpha
 */
@WebService(serviceName = "ProviderOld")
public class ProviderOld {

    private static final String EXCHANGE_NAME = "configuration";
    private static final Integer ID = 1;
    private ProviderBean bean = new ProviderBean(ID, 1, 1);

    /*creation du listenner
     / *Appelle config pour congigure le provider
     * */
    /**
     * This is a sample web service operation
     *
     * @return
     */
    @WebMethod(operationName = "hello")
    public String hello() {
        String s = bean.fillString('X', bean.getSizeDataExchange());
        try {
            Thread.sleep(bean.getProcessingTime());
        } catch (InterruptedException ex) {
            Logger.getLogger(Provider1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "> " + s + " <";
    }

    /**
     * function that get the configuration from the rabbitMq queue
     *
     * @return the message from the queue
     * @throws java.lang.Exception
     */
    @WebMethod(operationName = "getConfig")
    public String getConfig() throws Exception {

        /* calling connectionFactory to create a custome connexion with
         * rabbitMQ server information.
         */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);

        // establish the connection with RabbitMQ server using our factory.
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // linking with the queue 
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        // define the reciever 
        String who = "provider" + ID;
        channel.queueBind(queueName, EXCHANGE_NAME, who);

        QueueingConsumer cons = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, cons);

        // waiting for configuration
        while (true) {
            
            // retrieve the message
            QueueingConsumer.Delivery delivery = cons.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();
            
            // parse the message
            String[] content = message.split(";");
            if (content.length != 2) {
                return null;
            } else {
                // dataExchange | processingTime
                listenner(Integer.parseInt(content[0]), Integer.parseInt(content[1]));
            }
            
            return " [x] Received '" + routingKey + "':'" + message + "'";
        }

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listenner")
    public Boolean listenner(@WebParam(name = "dataExchange") Integer dataExchange, @WebParam(name = "processingTime") Integer processingTime) {

        this.bean.setProcessingTime(processingTime);
        this.bean.setSizeDataExchange(dataExchange);

        return true;
    }

}
