/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package providers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 *
 * @author samsung
 */
public class ProviderBoss {

    private static final String EXCHANGE_NAME = "configuration";
    private ProviderBean bean;
    
    
    public void setBean(ProviderBean bean) {
        this.bean = bean ;
    }
    
    public String getConfig(Integer ID) throws Exception {

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
                return "oussama";
            } else {
                // dataExchange | processingTime
                listenner(Integer.parseInt(content[0]), Integer.parseInt(content[1]));
            }

            return " [x] Received '" + routingKey + "':'" + message + "'";
        }
        
    }

    public Boolean listenner(Integer dataExchange, Integer processingTime) {

        this.bean.setProcessingTime(processingTime);
        this.bean.setSizeDataExchange(dataExchange);

        return true;
    }
    
}
