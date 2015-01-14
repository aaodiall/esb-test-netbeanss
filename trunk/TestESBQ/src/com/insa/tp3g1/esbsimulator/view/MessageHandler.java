/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.insa.tp3g1.esbsimulator.view;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *
 * @author alpha
 */
public class MessageHandler implements MessageHandlerReceiver, MessageHandlerSender{
    
    private static final String EXCHANGE_NAME = "configuration";
     private static final String EXCHANGE_NAME_START = "start";
    
    @Override
    public String sendConfig(String who, String config)   throws Exception {
        
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
        
        // We're connected now, to the broker on the cloud machine.
        // If we wanted to connect to a broker on a the local machine we'd simply specify "localhost" as IP adresse.
        // creating a "configuration" direct channel/queue
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        
        // the message and the distination
        String forWho = who;
        String message = config;
        
        // publish the message
        channel.basicPublish(EXCHANGE_NAME, forWho, null, message.getBytes());
        
        // close the queue and the connexion
        channel.close();
        connection.close();
        return " [x] Sent '" + forWho + "':'" + message + "'";
    }
    
  
    
    
}
